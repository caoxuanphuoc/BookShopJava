package model.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class DBConnection {
	public static String strConn = "jdbc:sqlserver://localhost:1433;databaseName=BanSach;user=sa;password=123";
	private static Connection connection;

	public DBConnection() {
		super();
	}

	public static void connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(strConn);
			System.out.println("Da ket noi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (connection.isClosed() == false) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] arg) {
		connect();
	}
	// Thá»±c thi hĂ m tráº£ vá»� khĂ¡c báº£ng
	public Object executeScalarFunction(String name, Vector<Object[]> paramsIn, Vector<Object[]> paramsOut) throws SQLException {
		// Count number parameter
		int count = 0;
		if (paramsIn != null)
			count += paramsIn.size();

		String query = createQueryScalarFunction(name, count);
		CallableStatement statement = connection.prepareCall(query);

		// Set up ParamsIn
		if (paramsIn != null && paramsIn.size() > 0)
			setUpParameterIn(statement, paramsIn);

		// Set up ParamOut
		if (paramsOut != null && paramsOut.size() > 0)
			setUpParameterOut(statement, paramsOut);

		// Set up Execute query
		statement.executeUpdate();

		return getResultOfScalarFunction(statement);
	}

	// Thá»±c thi hĂ m tráº£ vá»� má»™t báº£ng
	public ResultSet executeTableFunction(String name, Vector<Object[]> paramsIn) throws SQLException {
		String query = null;
		if (paramsIn == null)
			query = createQueryTableFunction(name, 0);
		else
			query = createQueryTableFunction(name, paramsIn.size());
		CallableStatement statement = connection.prepareCall(query);

		if (paramsIn != null && paramsIn.size() > 0) {
			setUpParameterIn(statement, paramsIn);
		}

		ResultSet result = statement.executeQuery();
		
		return result;
	}

	// Thá»±c thi cĂ³ thá»ƒ tráº£ vá»� hay khĂ´ng giĂ¡ trá»‹ khĂ¡c báº£ng
	public Object[] executeProcedure(String name, Vector<Object[]> paramsIn, Vector<Object[]> paramsOut,
			Vector<Object[]> paramsInOut) throws SQLException {
		// Count number parameter
		int count = 0;
		if (paramsIn != null)
			count += paramsIn.size();
		if (paramsOut != null)
			count += paramsOut.size();
		if (paramsInOut != null)
			count += paramsInOut.size();

		String query = createQueryProcedure(name, count);
		CallableStatement statement = connection.prepareCall(query);

		// Set up ParamsIn
		if (paramsIn != null && paramsIn.size() > 0)
			setUpParameterIn(statement, paramsIn);

		// Set up ParamsInOut
		if (paramsInOut != null && paramsInOut.size() > 0)
			setUpParameterInOut(statement, paramsInOut);

		// Set up ParamOut
		if (paramsOut != null && paramsOut.size() > 0)
			setUpParameterOut(statement, paramsOut);
		
		// Set up Execute query
		statement.executeUpdate();

		// Get result after execute
		Vector<Object> result = getResultOfProcedure(statement, paramsOut, paramsInOut);
		return result.toArray();
	}

	// Khá»Ÿi táº¡o báº£ng giĂ¡ trá»‹ cho ParamsIn, ParamOut
	public Vector<Object[]> createParams(int[] index, Object[] value) {
		Vector<Object[]> result = new Vector<>();

		if (index != null && value != null && index.length == value.length) {
			for (int i = 0; i < index.length; i++) {
				Object[] objects = new Object[] { index[i], value[i] };
				result.add(objects);
			}
		} else
			return null; // Tráº£ vá»� Null náº¿u 2 Ä‘á»‘i sá»‘ sai khĂ¡c kĂ­ch thÆ°á»›c

		return result;
	}

	// Khá»Ÿi táº¡o báº£ng giĂ¡ trá»‹ cho ParamInOut
	public Vector<Object[]> createParams(int[] index, Object[] type, Object[] value) {
		Vector<Object[]> result = new Vector<>();

		if (index != null && value != null && type != null && index.length == value.length
				&& index.length == type.length) {
			for (int i = 0; i < index.length; i++) {
				Object[] objects = new Object[] { index[i], type[i], value[i] };
				result.add(objects);
			}
		} else
			return null; // Tráº£ vá»� Null náº¿u 3 Ä‘á»‘i sá»‘ sai khĂ¡c kĂ­ch thÆ°á»›c

		return result;
	}
	
	// Khá»Ÿi táº¡o cĂ¢u truy váº¥n tá»›i tráº£ vá»� má»™t table
	private String createQueryScalarFunction(String name, int countParamsIn) {
		StringBuffer sBuffer = new StringBuffer("{? = Call " + name + " (");
		for (int i = 0; i < countParamsIn; i++) {
			sBuffer.append("?,");
		}
		sBuffer.replace(sBuffer.length() - 1, sBuffer.length(), ")}");
		return sBuffer.toString();
	}

	// Khá»Ÿi táº¡o cĂ¢u truy váº¥n tá»›i tráº£ vá»� má»™t table
	private String createQueryTableFunction(String name, int count) {
		StringBuffer sBuffer = new StringBuffer("Select * From " + name + "(");
		for (int i = 0; i < count; i++) {
			sBuffer.append("?,");
		}
		if (count > 0)
			sBuffer.replace(sBuffer.length() - 1, sBuffer.length(), ")");
		else
			sBuffer.append(")");
		return sBuffer.toString();
	}

	// Táº¡o cĂ¢u truy váº¥n tá»›i procedure
	private String createQueryProcedure(String name, int count) {
		StringBuffer sBuffer = new StringBuffer("{Call " + name + " (");
		for (int i = 0; i < count; i++) {
			sBuffer.append("?,");
		}
		sBuffer.replace(sBuffer.length() - 1, sBuffer.length(), ")}");
		return sBuffer.toString();
	}
	
	// Láº¥y káº¿t quáº£ tráº£ vá»� sau khi truy váº¥n tá»›i procedure
	private Object getResultOfScalarFunction(CallableStatement statement) throws SQLException {
		return statement.getObject(1);
	}

	// Láº¥y káº¿t quáº£ tráº£ vá»� sau khi truy váº¥n tá»›i procedure
	private Vector<Object> getResultOfProcedure(CallableStatement statement, Vector<Object[]> paramsOut,
			Vector<Object[]> paramsInOut) throws SQLException {
		Vector<Object> result = new Vector<>();

		// Get index of params out
		int[] indexOut = getIndexParams(paramsOut);

		// Get index of params in out
		int[] indexInOut = getIndexParams(paramsInOut);

		// Set up index of result = Sort(index out + index in out)
		int[] index = setUpIndexResult(indexOut, indexInOut);

		for (int i = 0; i < index.length; i++) {
			result.addElement(statement.getObject(index[i]));
		}

		return result;
	}

	// Khá»Ÿi táº¡o vá»‹ trĂ­ tráº£ vá»� cá»§a cĂ¡c pháº§n tá»­ trong truy váº¥n
	private int[] setUpIndexResult(int[] indexOut, int[] indexInOut) {
		int size = 0;
		if (indexOut != null)
			size += indexOut.length;
		if (indexInOut != null)
			size += indexInOut.length;

		int[] result = new int[size];
		int index = 0;

		// Add element to result
		if (indexOut != null) {
			for (Integer value : indexOut) {
				result[index] = value;
				index++;
			}
		}
		if (indexInOut != null) {
			for (Integer value : indexInOut) {
				result[index] = value;
				index++;
			}
		}

		// Sort and return result
		Arrays.sort(result);
		return result;
	}

	// Láº¥y vá»‹ trĂ­ cá»§a Ä‘á»‘i sá»‘ trong cĂ¢u truy váº¥n
	private int[] getIndexParams(Vector<Object[]> params) {
		if (params == null)
			return new int[0];
		int[] result = new int[params.size()];
		for (int i = 0; i < params.size(); i++) {
			result[i] = (int) params.elementAt(i)[0];
		}
		return result;
	}

	// GĂ¡n giĂ¡ trá»‹ Ä‘á»‘i sá»‘ cho cĂ¢u truy váº¥n
	private void setUpParameterInOut(CallableStatement statement, Vector<Object[]> value)
			throws SQLException {
		//Value lĂ  má»™t máº£ng cĂ³ cáº¥u trĂºc, [1]: vá»‹ trĂ­ trong cĂ¢u truy váº¥n, [2]: kiá»ƒu dá»¯ liá»‡u tráº£ vá»� (Types), [3]: giĂ¡ trá»‹ truyá»�n vĂ o
		
		if (value == null)
			return;
		for (int i = 0; i < value.size(); i++) {
			Object[] element = (Object[]) value.elementAt(i);
			statement.registerOutParameter((int) element[0], (int) element[1]);
			statement.setObject((int) element[0], element[2]);
		}
	}

	// GĂ¡n giĂ¡ trá»‹ Ä‘á»‘i sá»‘ cho cĂ¢u truy váº¥n
	private void setUpParameterOut(CallableStatement statement, Vector<Object[]> value) throws SQLException {
		//Value lĂ  má»™t máº£ng cĂ³ cáº¥u trĂºc, [1]: vá»‹ trĂ­ trong cĂ¢u truy váº¥n, [2]: kiá»ƒu dá»¯ liá»‡u tráº£ vá»� (Types)
		
		if (value == null)
			return;
		for (int i = 0; i < value.size(); i++) {
			Object[] element = (Object[]) value.elementAt(i);
			statement.registerOutParameter((int) element[0], (int) element[1]);
		}
	}

	// GĂ¡n giĂ¡ trá»‹ Ä‘á»‘i sá»‘ cho cĂ¢u truy váº¥n
	private void setUpParameterIn(CallableStatement statement, Vector<Object[]> value) throws SQLException {
		//Value lĂ  má»™t máº£ng cĂ³ cáº¥u trĂºc, [1]: vá»‹ trĂ­ trong cĂ¢u truy váº¥n, [2]: giĂ¡ trá»‹ truyá»�n vĂ o
		
		if (value == null)
			return;
		for (int i = 0; i < value.size(); i++) {
			Object[] element = (Object[]) value.elementAt(i);
			statement.setObject((int) element[0], element[1]);
		}
	}
}
