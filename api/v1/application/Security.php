<?php

class Security {
	public static function encrypt($string): string {
		return password_hash($string, PASSWORD_BCRYPT);
	}

	public static function decrypt($password, $hash): bool {
		return password_verify($password, $hash);
	}
}