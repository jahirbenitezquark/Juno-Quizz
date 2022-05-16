<?php

class Utils {
	const isDebug = true;

	public static function isDebug(): bool {
		return self::isDebug;
	}

    public static function validateParams($params): ?string {
        $error = null;

        foreach ($params as $param) {
            if(!isset($_POST[$param])) {
                if(self::isDebug()) {
                    $error = $param;
                } else {
                    $error = "paramName";
                }

                break;
            }
        }

        return $error;
    }

    public static function validatePostParams($params): bool {
        $correct = true;
        $result = new Result();

        foreach ($params as $param) {
            if(!isset($_POST[$param])) {
                $result->setCode(Constants::errorCodeMissingParams);
                $result->setMessage(Constants::errorCodeMissingParamsDescription);

                if(self::isDebug()) {
                    $result->setResponse($param);
                } else {
                    $result->setResponse(null);
                }

                $correct = false;

                header(Constants::contentApplicationJson);
                echo json_encode($result);
                break;
            }
        }

        return $correct;
    }

    public static function hasParam($param): bool {
        if(isset($param)) {
            return true;
        }

        return false;
    }

    public static function addslashes($str){
        return str_replace("'", "''", $str);
    }

    public static function getMissingParamsError(?string $error): Result {
        $result = new Result();

        $result->setCode(Constants::errorCodeMissingParams);
        $result->setMessage(Constants::errorCodeMissingParamsDescription);

        if(self::isDebug()) {
            $result->setResponse($error);
        } else {
            $result->setResponse(null);
        }

        return $result;
    }

    public static function getDatabaseError(string $ex): Result {
        $result = new Result();

        if(strpos($ex, 'Duplicate entry') !== false) {
            $result->setCode(Constants::errorCodeDuplicateEntry);
            $result->setMessage(Constants::errorCodeDuplicateEntryDescription);
        } else {
            $result->setCode(Constants::errorCodeConnectionDatabase);

            if(self::isDebug()) {
                $result->setMessage($ex);
            } else {
                $result->setMessage(Constants::errorCodeConnectionDatabaseDescription);
            }
        }

        $result->setResponse(null);

        return $result;
    }

    public static function getUnknownAction(): Result {
        $result = new Result();

        $result->setCode(Constants::errorCodeUnknownAction);
        $result->setMessage(Constants::errorCodeUnknownActionDescription);
        $result->setResponse(null);

        return $result;
    }
}