package fr.jouhs.sec;

public interface SecurityParams {
	public static final String JWT_HEADER_NAME="Authorization";
	public static final String JWT_SECRET="jaouad.ouhs@gmail.com";
	public static final long JWT_EXPIRATION = 10 * 24 * 3600;
	public static final String JWT_PREFIX ="Jouhs ";
}
