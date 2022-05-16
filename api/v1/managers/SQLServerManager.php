<?php /** @noinspection PhpUnused */

include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/application/SQLServer.php';

class SQLServerManager extends SQLServer {
	private static $instance = null;
	private $connection;

	private function __construct() {
        $connectionInfo = array( "Database"=>$this->getDatabase(), "UID"=>$this->getUserName(), "PWD"=>$this->getPassword());
        $this->connection = sqlsrv_connect($this->getServerName(), $connectionInfo);

        if(!$this->connection) {
            throw new RuntimeException('Unable to connect to database');
        }
    }

	public function __destruct() {
		self::closeConnection();
	}

	// Magic method clone is empty to prevent duplication of connection
	private function __clone() {

	}

	public static function getInstance() {
		if (self::$instance === null) {
			self::$instance = new SQLServerManager();
		}

		return self::$instance;
	}

	protected function getConnection() {
		return $this->connection;
	}

	protected function closeConnection(): bool {
		return sqlsrv_close($this->connection);
	}

    public function executeQuery(string $query) {
        return sqlsrv_query($this->getConnection(), $query);
    }

    public function hasRows($statement): bool {
	    return sqlsrv_has_rows($statement);
    }

    public function numberRows($statement): int {
	    return sqlsrv_num_rows($statement);
    }

    public function rowsAffected($statement): int {
        return sqlsrv_rows_affected($statement);
    }

    public function fetch($statement) {
        return sqlsrv_fetch_array($statement, SQLSRV_FETCH_ASSOC);
    }

    public function free($statement): bool {
        return sqlsrv_free_stmt($statement);
    }

	protected function getServerName(): string {
		return "54.191.166.97";
	}

	protected function getPort(): string {
		return "1434";
	}

	protected function getUserName(): string {
		return "sa";
	}

	protected function getPassword(): string {
		return "CodeQuark$$";
	}

	protected function getDatabase(): string {
		return "DB_JunoApp";
	}
}