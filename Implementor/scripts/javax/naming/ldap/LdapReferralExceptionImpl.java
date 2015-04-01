package javax.naming.ldap;

public class LdapReferralExceptionImpl extends javax.naming.ldap.LdapReferralException {


	protected LdapReferralExceptionImpl() {
		super();
	}

	protected LdapReferralExceptionImpl(java.lang.String var1) {
		super(var1);
	}

	public javax.naming.Context getReferralContext(java.util.Hashtable var1) throws javax.naming.NamingException {
		return null;
	}

	public javax.naming.Context getReferralContext(java.util.Hashtable var1, javax.naming.ldap.Control[] var2) throws javax.naming.NamingException {
		return null;
	}

	public javax.naming.Context getReferralContext() throws javax.naming.NamingException {
		return null;
	}

	public java.lang.Object getReferralInfo() {
		return null;
	}

	public boolean skipReferral() {
		return false;
	}

	public void retryReferral() {
	}

}