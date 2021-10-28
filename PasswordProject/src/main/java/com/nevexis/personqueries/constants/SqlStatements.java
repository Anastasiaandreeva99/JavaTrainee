package com.nevexis.personqueries.constants;

public class SqlStatements {
	public static final String getUserRo = "SELECT 1 FROM user where name = ? and password = ?";
	public static final String getUser = "SELECT * FROM user where name = ? and password = ?";
	public static final String updatePassword = "UPDATE `user` SET `password` = ? , last_modified = now() WHERE (`id` = '1');";
	public static final String insertInHistory = "INSERT INTO `password_expire` (`last_date_modified`, `user_id`, `password`) VALUES (now(), (SELECT id FROM `user` WHERE `name` = ? LIMIT 1), ?);";
	public static final String insertInHistoryExpired = "INSERT INTO `password_expire` (`last_date_modified`, `user_id`, `password`) VALUES (now()-interval 2 month, (SELECT id FROM `user` WHERE `name` = ? LIMIT 1), ?);";
	public static final String isPasswordExpire = "SELECT 1 FROM user where name = ?  and last_modified + interval 10 day > now();";
	public static final String isPasswordInHistory = "SELECT 1 FROM password_expire pe JOIN `user` u ON  user_id=u.id WHERE u.name = ? AND last_date_modified > now() - interval 60 day AND pe.password = ?;";
	public static final String insertNewPerson = "insert into user (name,password,last_modified) values (?,?,now());";
	public static final String insertExpiredPerson = "insert into user (name,password,last_modified) values (?,?,now()- interval 2 month);";
	public static final String deleteUsers = " delete from user ";
	public static final String truncateHistory = "  truncate password_expire;";

}
