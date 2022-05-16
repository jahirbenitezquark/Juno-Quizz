<?php

class Result implements JsonSerializable {
    private int $code;
    private string $message;
    private $response;

    public function setCode(int $code): void {
        $this->code = $code;
    }

    public function setMessage(string $message): void {
        $this->message = $message;
    }

    public function setResponse($response) {
        $this->response = $response;
    }

    public function getCode(): int {
        return $this->code;
    }

    public function getMessage(): string {
        return $this->message;
    }

    public function getResponse() {
        return $this->response;
    }

    public function jsonSerialize(): array {
        return [
            'code' => $this->getCode(),
            'message' => $this->getMessage(),
            'response' => $this->getResponse()
        ];
    }

    public function __toString() {
        $array = [
            'code' => $this->getCode(),
            'message' => $this->getMessage(),
            'response' => $this->getResponse()
        ];

        return json_encode($array);
    }
}