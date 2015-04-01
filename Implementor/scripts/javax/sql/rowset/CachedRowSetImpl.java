package javax.sql.rowset;

public class CachedRowSetImpl implements javax.sql.rowset.CachedRowSet {


	public void populate(java.sql.ResultSet var1) throws java.sql.SQLException {
	}

	public void populate(java.sql.ResultSet var1, int var2) throws java.sql.SQLException {
	}

	public void acceptChanges(java.sql.Connection var1) throws javax.sql.rowset.spi.SyncProviderException {
	}

	public void acceptChanges() throws javax.sql.rowset.spi.SyncProviderException {
	}

	public void restoreOriginal() throws java.sql.SQLException {
	}

	public void undoDelete() throws java.sql.SQLException {
	}

	public void undoInsert() throws java.sql.SQLException {
	}

	public void undoUpdate() throws java.sql.SQLException {
	}

	public boolean columnUpdated(int var1) throws java.sql.SQLException {
		return false;
	}

	public boolean columnUpdated(java.lang.String var1) throws java.sql.SQLException {
		return false;
	}

	public java.util.Collection toCollection(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.util.Collection toCollection() throws java.sql.SQLException {
		return null;
	}

	public java.util.Collection toCollection(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public javax.sql.rowset.spi.SyncProvider getSyncProvider() throws java.sql.SQLException {
		return null;
	}

	public void setSyncProvider(java.lang.String var1) throws java.sql.SQLException {
	}

	public void setMetaData(javax.sql.RowSetMetaData var1) throws java.sql.SQLException {
	}

	public java.sql.ResultSet getOriginal() throws java.sql.SQLException {
		return null;
	}

	public java.sql.ResultSet getOriginalRow() throws java.sql.SQLException {
		return null;
	}

	public void setOriginalRow() throws java.sql.SQLException {
	}

	public java.lang.String getTableName() throws java.sql.SQLException {
		return null;
	}

	public void setTableName(java.lang.String var1) throws java.sql.SQLException {
	}

	public int[] getKeyColumns() throws java.sql.SQLException {
		return null;
	}

	public void setKeyColumns(int[] var1) throws java.sql.SQLException {
	}

	public javax.sql.RowSet createShared() throws java.sql.SQLException {
		return null;
	}

	public javax.sql.rowset.CachedRowSet createCopy() throws java.sql.SQLException {
		return null;
	}

	public javax.sql.rowset.CachedRowSet createCopySchema() throws java.sql.SQLException {
		return null;
	}

	public javax.sql.rowset.CachedRowSet createCopyNoConstraints() throws java.sql.SQLException {
		return null;
	}

	public javax.sql.rowset.RowSetWarning getRowSetWarnings() throws java.sql.SQLException {
		return null;
	}

	public boolean getShowDeleted() throws java.sql.SQLException {
		return false;
	}

	public void setShowDeleted(boolean var1) throws java.sql.SQLException {
	}

	public void commit() throws java.sql.SQLException {
	}

	public void rowSetPopulated(javax.sql.RowSetEvent var1, int var2) throws java.sql.SQLException {
	}

	public void setPageSize(int var1) throws java.sql.SQLException {
	}

	public int getPageSize() {
		return 0;
	}

	public boolean nextPage() throws java.sql.SQLException {
		return false;
	}

	public boolean previousPage() throws java.sql.SQLException {
		return false;
	}

	public void rollback(java.sql.Savepoint var1) throws java.sql.SQLException {
	}

	public void rollback() throws java.sql.SQLException {
	}

	public int size() {
		return 0;
	}

	public void execute(java.sql.Connection var1) throws java.sql.SQLException {
	}

	public void release() throws java.sql.SQLException {
	}

	public java.lang.String getUrl() throws java.sql.SQLException {
		return null;
	}

	public void setUrl(java.lang.String var1) throws java.sql.SQLException {
	}

	public java.lang.String getDataSourceName() {
		return null;
	}

	public void setDataSourceName(java.lang.String var1) throws java.sql.SQLException {
	}

	public java.lang.String getUsername() {
		return null;
	}

	public void setUsername(java.lang.String var1) throws java.sql.SQLException {
	}

	public java.lang.String getPassword() {
		return null;
	}

	public void setPassword(java.lang.String var1) throws java.sql.SQLException {
	}

	public int getTransactionIsolation() {
		return 0;
	}

	public void setTransactionIsolation(int var1) throws java.sql.SQLException {
	}

	public java.util.Map getTypeMap() throws java.sql.SQLException {
		return null;
	}

	public void setTypeMap(java.util.Map var1) throws java.sql.SQLException {
	}

	public java.lang.String getCommand() {
		return null;
	}

	public void setCommand(java.lang.String var1) throws java.sql.SQLException {
	}

	public int getMaxFieldSize() throws java.sql.SQLException {
		return 0;
	}

	public void setMaxFieldSize(int var1) throws java.sql.SQLException {
	}

	public int getMaxRows() throws java.sql.SQLException {
		return 0;
	}

	public void setMaxRows(int var1) throws java.sql.SQLException {
	}

	public boolean getEscapeProcessing() throws java.sql.SQLException {
		return false;
	}

	public void setEscapeProcessing(boolean var1) throws java.sql.SQLException {
	}

	public int getQueryTimeout() throws java.sql.SQLException {
		return 0;
	}

	public void setQueryTimeout(int var1) throws java.sql.SQLException {
	}

	public void setConcurrency(int var1) throws java.sql.SQLException {
	}

	public void setNull(java.lang.String var1, int var2) throws java.sql.SQLException {
	}

	public void setNull(int var1, int var2, java.lang.String var3) throws java.sql.SQLException {
	}

	public void setNull(int var1, int var2) throws java.sql.SQLException {
	}

	public void setNull(java.lang.String var1, int var2, java.lang.String var3) throws java.sql.SQLException {
	}

	public void setBigDecimal(int var1, java.math.BigDecimal var2) throws java.sql.SQLException {
	}

	public void setBigDecimal(java.lang.String var1, java.math.BigDecimal var2) throws java.sql.SQLException {
	}

	public void setString(java.lang.String var1, java.lang.String var2) throws java.sql.SQLException {
	}

	public void setString(int var1, java.lang.String var2) throws java.sql.SQLException {
	}

	public void setBytes(java.lang.String var1, byte[] var2) throws java.sql.SQLException {
	}

	public void setBytes(int var1, byte[] var2) throws java.sql.SQLException {
	}

	public void setAsciiStream(int var1, java.io.InputStream var2, int var3) throws java.sql.SQLException {
	}

	public void setAsciiStream(java.lang.String var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void setAsciiStream(int var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void setAsciiStream(java.lang.String var1, java.io.InputStream var2, int var3) throws java.sql.SQLException {
	}

	public void setBinaryStream(int var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void setBinaryStream(java.lang.String var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void setBinaryStream(java.lang.String var1, java.io.InputStream var2, int var3) throws java.sql.SQLException {
	}

	public void setBinaryStream(int var1, java.io.InputStream var2, int var3) throws java.sql.SQLException {
	}

	public void setCharacterStream(java.lang.String var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void setCharacterStream(int var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void setCharacterStream(java.lang.String var1, java.io.Reader var2, int var3) throws java.sql.SQLException {
	}

	public void setCharacterStream(int var1, java.io.Reader var2, int var3) throws java.sql.SQLException {
	}

	public void setNCharacterStream(java.lang.String var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void setNCharacterStream(java.lang.String var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void setNCharacterStream(int var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void setNCharacterStream(int var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void setRef(int var1, java.sql.Ref var2) throws java.sql.SQLException {
	}

	public void setBlob(int var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void setBlob(java.lang.String var1, java.io.InputStream var2, long var3) throws java.sql.SQLException {
	}

	public void setBlob(java.lang.String var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void setBlob(int var1, java.io.InputStream var2, long var3) throws java.sql.SQLException {
	}

	public void setBlob(int var1, java.sql.Blob var2) throws java.sql.SQLException {
	}

	public void setBlob(java.lang.String var1, java.sql.Blob var2) throws java.sql.SQLException {
	}

	public void setClob(int var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void setClob(java.lang.String var1, java.sql.Clob var2) throws java.sql.SQLException {
	}

	public void setClob(int var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void setClob(java.lang.String var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void setClob(int var1, java.sql.Clob var2) throws java.sql.SQLException {
	}

	public void setClob(java.lang.String var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void setArray(int var1, java.sql.Array var2) throws java.sql.SQLException {
	}

	public void clearParameters() throws java.sql.SQLException {
	}

	public void addRowSetListener(javax.sql.RowSetListener var1) {
	}

	public void removeRowSetListener(javax.sql.RowSetListener var1) {
	}

	public void setSQLXML(int var1, java.sql.SQLXML var2) throws java.sql.SQLException {
	}

	public void setSQLXML(java.lang.String var1, java.sql.SQLXML var2) throws java.sql.SQLException {
	}

	public void setRowId(java.lang.String var1, java.sql.RowId var2) throws java.sql.SQLException {
	}

	public void setRowId(int var1, java.sql.RowId var2) throws java.sql.SQLException {
	}

	public void setNString(java.lang.String var1, java.lang.String var2) throws java.sql.SQLException {
	}

	public void setNString(int var1, java.lang.String var2) throws java.sql.SQLException {
	}

	public void setNClob(java.lang.String var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void setNClob(int var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void setNClob(int var1, java.sql.NClob var2) throws java.sql.SQLException {
	}

	public void setNClob(int var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void setNClob(java.lang.String var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void setNClob(java.lang.String var1, java.sql.NClob var2) throws java.sql.SQLException {
	}

	public void setTime(int var1, java.sql.Time var2) throws java.sql.SQLException {
	}

	public void setTime(java.lang.String var1, java.sql.Time var2) throws java.sql.SQLException {
	}

	public void setTime(int var1, java.sql.Time var2, java.util.Calendar var3) throws java.sql.SQLException {
	}

	public void setTime(java.lang.String var1, java.sql.Time var2, java.util.Calendar var3) throws java.sql.SQLException {
	}

	public void setDate(int var1, java.sql.Date var2) throws java.sql.SQLException {
	}

	public void setDate(int var1, java.sql.Date var2, java.util.Calendar var3) throws java.sql.SQLException {
	}

	public void setDate(java.lang.String var1, java.sql.Date var2, java.util.Calendar var3) throws java.sql.SQLException {
	}

	public void setDate(java.lang.String var1, java.sql.Date var2) throws java.sql.SQLException {
	}

	public void setObject(int var1, java.lang.Object var2, int var3, int var4) throws java.sql.SQLException {
	}

	public void setObject(java.lang.String var1, java.lang.Object var2, int var3) throws java.sql.SQLException {
	}

	public void setObject(int var1, java.lang.Object var2, int var3) throws java.sql.SQLException {
	}

	public void setObject(java.lang.String var1, java.lang.Object var2, int var3, int var4) throws java.sql.SQLException {
	}

	public void setObject(java.lang.String var1, java.lang.Object var2) throws java.sql.SQLException {
	}

	public void setObject(int var1, java.lang.Object var2) throws java.sql.SQLException {
	}

	public void setURL(int var1, java.net.URL var2) throws java.sql.SQLException {
	}

	public void setType(int var1) throws java.sql.SQLException {
	}

	public void execute() throws java.sql.SQLException {
	}

	public void setReadOnly(boolean var1) throws java.sql.SQLException {
	}

	public void setBoolean(java.lang.String var1, boolean var2) throws java.sql.SQLException {
	}

	public void setBoolean(int var1, boolean var2) throws java.sql.SQLException {
	}

	public void setByte(int var1, byte var2) throws java.sql.SQLException {
	}

	public void setByte(java.lang.String var1, byte var2) throws java.sql.SQLException {
	}

	public void setShort(java.lang.String var1, short var2) throws java.sql.SQLException {
	}

	public void setShort(int var1, short var2) throws java.sql.SQLException {
	}

	public void setInt(int var1, int var2) throws java.sql.SQLException {
	}

	public void setInt(java.lang.String var1, int var2) throws java.sql.SQLException {
	}

	public void setLong(int var1, long var2) throws java.sql.SQLException {
	}

	public void setLong(java.lang.String var1, long var2) throws java.sql.SQLException {
	}

	public void setFloat(java.lang.String var1, float var2) throws java.sql.SQLException {
	}

	public void setFloat(int var1, float var2) throws java.sql.SQLException {
	}

	public void setDouble(java.lang.String var1, double var2) throws java.sql.SQLException {
	}

	public void setDouble(int var1, double var2) throws java.sql.SQLException {
	}

	public boolean isReadOnly() {
		return false;
	}

	public void setTimestamp(int var1, java.sql.Timestamp var2, java.util.Calendar var3) throws java.sql.SQLException {
	}

	public void setTimestamp(java.lang.String var1, java.sql.Timestamp var2, java.util.Calendar var3) throws java.sql.SQLException {
	}

	public void setTimestamp(java.lang.String var1, java.sql.Timestamp var2) throws java.sql.SQLException {
	}

	public void setTimestamp(int var1, java.sql.Timestamp var2) throws java.sql.SQLException {
	}

	public boolean isClosed() throws java.sql.SQLException {
		return false;
	}

	public boolean wasNull() throws java.sql.SQLException {
		return false;
	}

	public java.math.BigDecimal getBigDecimal(int var1, int var2) throws java.sql.SQLException {
		return null;
	}

	public java.math.BigDecimal getBigDecimal(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.math.BigDecimal getBigDecimal(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.math.BigDecimal getBigDecimal(java.lang.String var1, int var2) throws java.sql.SQLException {
		return null;
	}

	public java.io.InputStream getAsciiStream(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.io.InputStream getAsciiStream(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.io.InputStream getUnicodeStream(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.io.InputStream getUnicodeStream(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.io.InputStream getBinaryStream(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.io.InputStream getBinaryStream(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.SQLWarning getWarnings() throws java.sql.SQLException {
		return null;
	}

	public void clearWarnings() throws java.sql.SQLException {
	}

	public java.lang.String getCursorName() throws java.sql.SQLException {
		return null;
	}

	public java.sql.ResultSetMetaData getMetaData() throws java.sql.SQLException {
		return null;
	}

	public int findColumn(java.lang.String var1) throws java.sql.SQLException {
		return 0;
	}

	public java.io.Reader getCharacterStream(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.io.Reader getCharacterStream(int var1) throws java.sql.SQLException {
		return null;
	}

	public boolean isBeforeFirst() throws java.sql.SQLException {
		return false;
	}

	public boolean isAfterLast() throws java.sql.SQLException {
		return false;
	}

	public boolean isLast() throws java.sql.SQLException {
		return false;
	}

	public void beforeFirst() throws java.sql.SQLException {
	}

	public void afterLast() throws java.sql.SQLException {
	}

	public int getRow() throws java.sql.SQLException {
		return 0;
	}

	public boolean absolute(int var1) throws java.sql.SQLException {
		return false;
	}

	public boolean relative(int var1) throws java.sql.SQLException {
		return false;
	}

	public void setFetchDirection(int var1) throws java.sql.SQLException {
	}

	public int getFetchDirection() throws java.sql.SQLException {
		return 0;
	}

	public void setFetchSize(int var1) throws java.sql.SQLException {
	}

	public int getFetchSize() throws java.sql.SQLException {
		return 0;
	}

	public int getConcurrency() throws java.sql.SQLException {
		return 0;
	}

	public boolean rowUpdated() throws java.sql.SQLException {
		return false;
	}

	public boolean rowInserted() throws java.sql.SQLException {
		return false;
	}

	public boolean rowDeleted() throws java.sql.SQLException {
		return false;
	}

	public void updateNull(int var1) throws java.sql.SQLException {
	}

	public void updateNull(java.lang.String var1) throws java.sql.SQLException {
	}

	public void updateBoolean(int var1, boolean var2) throws java.sql.SQLException {
	}

	public void updateBoolean(java.lang.String var1, boolean var2) throws java.sql.SQLException {
	}

	public void updateByte(int var1, byte var2) throws java.sql.SQLException {
	}

	public void updateByte(java.lang.String var1, byte var2) throws java.sql.SQLException {
	}

	public void updateShort(java.lang.String var1, short var2) throws java.sql.SQLException {
	}

	public void updateShort(int var1, short var2) throws java.sql.SQLException {
	}

	public void updateInt(int var1, int var2) throws java.sql.SQLException {
	}

	public void updateInt(java.lang.String var1, int var2) throws java.sql.SQLException {
	}

	public void updateLong(int var1, long var2) throws java.sql.SQLException {
	}

	public void updateLong(java.lang.String var1, long var2) throws java.sql.SQLException {
	}

	public void updateFloat(int var1, float var2) throws java.sql.SQLException {
	}

	public void updateFloat(java.lang.String var1, float var2) throws java.sql.SQLException {
	}

	public void updateDouble(int var1, double var2) throws java.sql.SQLException {
	}

	public void updateDouble(java.lang.String var1, double var2) throws java.sql.SQLException {
	}

	public void updateBigDecimal(java.lang.String var1, java.math.BigDecimal var2) throws java.sql.SQLException {
	}

	public void updateBigDecimal(int var1, java.math.BigDecimal var2) throws java.sql.SQLException {
	}

	public void updateString(int var1, java.lang.String var2) throws java.sql.SQLException {
	}

	public void updateString(java.lang.String var1, java.lang.String var2) throws java.sql.SQLException {
	}

	public void updateDate(int var1, java.sql.Date var2) throws java.sql.SQLException {
	}

	public void updateDate(java.lang.String var1, java.sql.Date var2) throws java.sql.SQLException {
	}

	public void updateTime(int var1, java.sql.Time var2) throws java.sql.SQLException {
	}

	public void updateTime(java.lang.String var1, java.sql.Time var2) throws java.sql.SQLException {
	}

	public void updateTimestamp(int var1, java.sql.Timestamp var2) throws java.sql.SQLException {
	}

	public void updateTimestamp(java.lang.String var1, java.sql.Timestamp var2) throws java.sql.SQLException {
	}

	public void updateAsciiStream(java.lang.String var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void updateAsciiStream(java.lang.String var1, java.io.InputStream var2, long var3) throws java.sql.SQLException {
	}

	public void updateAsciiStream(java.lang.String var1, java.io.InputStream var2, int var3) throws java.sql.SQLException {
	}

	public void updateAsciiStream(int var1, java.io.InputStream var2, int var3) throws java.sql.SQLException {
	}

	public void updateAsciiStream(int var1, java.io.InputStream var2, long var3) throws java.sql.SQLException {
	}

	public void updateAsciiStream(int var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void updateBinaryStream(int var1, java.io.InputStream var2, int var3) throws java.sql.SQLException {
	}

	public void updateBinaryStream(int var1, java.io.InputStream var2, long var3) throws java.sql.SQLException {
	}

	public void updateBinaryStream(java.lang.String var1, java.io.InputStream var2, long var3) throws java.sql.SQLException {
	}

	public void updateBinaryStream(java.lang.String var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void updateBinaryStream(int var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void updateBinaryStream(java.lang.String var1, java.io.InputStream var2, int var3) throws java.sql.SQLException {
	}

	public void updateCharacterStream(int var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void updateCharacterStream(java.lang.String var1, java.io.Reader var2, int var3) throws java.sql.SQLException {
	}

	public void updateCharacterStream(int var1, java.io.Reader var2, int var3) throws java.sql.SQLException {
	}

	public void updateCharacterStream(java.lang.String var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void updateCharacterStream(java.lang.String var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void updateCharacterStream(int var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void updateObject(java.lang.String var1, java.lang.Object var2, int var3) throws java.sql.SQLException {
	}

	public void updateObject(int var1, java.lang.Object var2) throws java.sql.SQLException {
	}

	public void updateObject(java.lang.String var1, java.lang.Object var2) throws java.sql.SQLException {
	}

	public void updateObject(int var1, java.lang.Object var2, int var3) throws java.sql.SQLException {
	}

	public void insertRow() throws java.sql.SQLException {
	}

	public void updateRow() throws java.sql.SQLException {
	}

	public void deleteRow() throws java.sql.SQLException {
	}

	public void refreshRow() throws java.sql.SQLException {
	}

	public void cancelRowUpdates() throws java.sql.SQLException {
	}

	public void moveToInsertRow() throws java.sql.SQLException {
	}

	public void moveToCurrentRow() throws java.sql.SQLException {
	}

	public java.sql.Blob getBlob(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Blob getBlob(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Clob getClob(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Clob getClob(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public void updateRef(java.lang.String var1, java.sql.Ref var2) throws java.sql.SQLException {
	}

	public void updateRef(int var1, java.sql.Ref var2) throws java.sql.SQLException {
	}

	public void updateBlob(java.lang.String var1, java.io.InputStream var2, long var3) throws java.sql.SQLException {
	}

	public void updateBlob(int var1, java.io.InputStream var2, long var3) throws java.sql.SQLException {
	}

	public void updateBlob(java.lang.String var1, java.sql.Blob var2) throws java.sql.SQLException {
	}

	public void updateBlob(int var1, java.sql.Blob var2) throws java.sql.SQLException {
	}

	public void updateBlob(int var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void updateBlob(java.lang.String var1, java.io.InputStream var2) throws java.sql.SQLException {
	}

	public void updateClob(java.lang.String var1, java.sql.Clob var2) throws java.sql.SQLException {
	}

	public void updateClob(java.lang.String var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void updateClob(int var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void updateClob(int var1, java.sql.Clob var2) throws java.sql.SQLException {
	}

	public void updateClob(java.lang.String var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void updateClob(int var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void updateArray(java.lang.String var1, java.sql.Array var2) throws java.sql.SQLException {
	}

	public void updateArray(int var1, java.sql.Array var2) throws java.sql.SQLException {
	}

	public java.sql.RowId getRowId(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.RowId getRowId(int var1) throws java.sql.SQLException {
		return null;
	}

	public void updateRowId(java.lang.String var1, java.sql.RowId var2) throws java.sql.SQLException {
	}

	public void updateRowId(int var1, java.sql.RowId var2) throws java.sql.SQLException {
	}

	public int getHoldability() throws java.sql.SQLException {
		return 0;
	}

	public void updateNString(java.lang.String var1, java.lang.String var2) throws java.sql.SQLException {
	}

	public void updateNString(int var1, java.lang.String var2) throws java.sql.SQLException {
	}

	public void updateNClob(int var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void updateNClob(int var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void updateNClob(java.lang.String var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void updateNClob(java.lang.String var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void updateNClob(java.lang.String var1, java.sql.NClob var2) throws java.sql.SQLException {
	}

	public void updateNClob(int var1, java.sql.NClob var2) throws java.sql.SQLException {
	}

	public java.sql.NClob getNClob(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.NClob getNClob(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.SQLXML getSQLXML(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.SQLXML getSQLXML(int var1) throws java.sql.SQLException {
		return null;
	}

	public void updateSQLXML(int var1, java.sql.SQLXML var2) throws java.sql.SQLException {
	}

	public void updateSQLXML(java.lang.String var1, java.sql.SQLXML var2) throws java.sql.SQLException {
	}

	public java.lang.String getNString(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.lang.String getNString(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.io.Reader getNCharacterStream(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.io.Reader getNCharacterStream(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public void updateNCharacterStream(int var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void updateNCharacterStream(java.lang.String var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public void updateNCharacterStream(java.lang.String var1, java.io.Reader var2) throws java.sql.SQLException {
	}

	public void updateNCharacterStream(int var1, java.io.Reader var2, long var3) throws java.sql.SQLException {
	}

	public java.lang.String getString(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.lang.String getString(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Time getTime(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Time getTime(java.lang.String var1, java.util.Calendar var2) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Time getTime(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Time getTime(int var1, java.util.Calendar var2) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Date getDate(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Date getDate(java.lang.String var1, java.util.Calendar var2) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Date getDate(int var1, java.util.Calendar var2) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Date getDate(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Timestamp getTimestamp(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Timestamp getTimestamp(int var1, java.util.Calendar var2) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Timestamp getTimestamp(java.lang.String var1, java.util.Calendar var2) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Timestamp getTimestamp(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public boolean last() throws java.sql.SQLException {
		return false;
	}

	public java.sql.Statement getStatement() throws java.sql.SQLException {
		return null;
	}

	public boolean isFirst() throws java.sql.SQLException {
		return false;
	}

	public void updateBytes(java.lang.String var1, byte[] var2) throws java.sql.SQLException {
	}

	public void updateBytes(int var1, byte[] var2) throws java.sql.SQLException {
	}

	public java.lang.Object getObject(java.lang.String var1, java.util.Map var2) throws java.sql.SQLException {
		return null;
	}

	public java.lang.Object getObject(int var1, java.lang.Class var2) throws java.sql.SQLException {
		return null;
	}

	public java.lang.Object getObject(int var1, java.util.Map var2) throws java.sql.SQLException {
		return null;
	}

	public java.lang.Object getObject(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.lang.Object getObject(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.lang.Object getObject(java.lang.String var1, java.lang.Class var2) throws java.sql.SQLException {
		return null;
	}

	public boolean getBoolean(java.lang.String var1) throws java.sql.SQLException {
		return false;
	}

	public boolean getBoolean(int var1) throws java.sql.SQLException {
		return false;
	}

	public byte getByte(java.lang.String var1) throws java.sql.SQLException {
		return 0;
	}

	public byte getByte(int var1) throws java.sql.SQLException {
		return 0;
	}

	public short getShort(java.lang.String var1) throws java.sql.SQLException {
		return 0;
	}

	public short getShort(int var1) throws java.sql.SQLException {
		return 0;
	}

	public int getInt(java.lang.String var1) throws java.sql.SQLException {
		return 0;
	}

	public int getInt(int var1) throws java.sql.SQLException {
		return 0;
	}

	public long getLong(java.lang.String var1) throws java.sql.SQLException {
		return 0;
	}

	public long getLong(int var1) throws java.sql.SQLException {
		return 0;
	}

	public float getFloat(int var1) throws java.sql.SQLException {
		return 0;
	}

	public float getFloat(java.lang.String var1) throws java.sql.SQLException {
		return 0;
	}

	public double getDouble(int var1) throws java.sql.SQLException {
		return 0;
	}

	public double getDouble(java.lang.String var1) throws java.sql.SQLException {
		return 0;
	}

	public byte[] getBytes(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public byte[] getBytes(int var1) throws java.sql.SQLException {
		return null;
	}

	public boolean next() throws java.sql.SQLException {
		return false;
	}

	public java.sql.Array getArray(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Array getArray(int var1) throws java.sql.SQLException {
		return null;
	}

	public java.net.URL getURL(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.net.URL getURL(int var1) throws java.sql.SQLException {
		return null;
	}

	public boolean first() throws java.sql.SQLException {
		return false;
	}

	public void close() throws java.sql.SQLException {
	}

	public int getType() throws java.sql.SQLException {
		return 0;
	}

	public java.sql.Ref getRef(java.lang.String var1) throws java.sql.SQLException {
		return null;
	}

	public java.sql.Ref getRef(int var1) throws java.sql.SQLException {
		return null;
	}

	public boolean previous() throws java.sql.SQLException {
		return false;
	}

	public boolean isWrapperFor(java.lang.Class var1) throws java.sql.SQLException {
		return false;
	}

	public java.lang.Object unwrap(java.lang.Class var1) throws java.sql.SQLException {
		return null;
	}

	public void setMatchColumn(java.lang.String[] var1) throws java.sql.SQLException {
	}

	public void setMatchColumn(java.lang.String var1) throws java.sql.SQLException {
	}

	public void setMatchColumn(int[] var1) throws java.sql.SQLException {
	}

	public void setMatchColumn(int var1) throws java.sql.SQLException {
	}

	public int[] getMatchColumnIndexes() throws java.sql.SQLException {
		return null;
	}

	public java.lang.String[] getMatchColumnNames() throws java.sql.SQLException {
		return null;
	}

	public void unsetMatchColumn(java.lang.String[] var1) throws java.sql.SQLException {
	}

	public void unsetMatchColumn(java.lang.String var1) throws java.sql.SQLException {
	}

	public void unsetMatchColumn(int[] var1) throws java.sql.SQLException {
	}

	public void unsetMatchColumn(int var1) throws java.sql.SQLException {
	}

}