<?php

abstract class SQLServer {
	protected abstract function getConnection();

	protected abstract function closeConnection();

	protected abstract function getServerName();

	protected abstract function getPort();

	protected abstract function getUsername();

	protected abstract function getPassword();

	protected abstract function getDatabase();
}