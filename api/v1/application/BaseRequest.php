<?php

abstract class BaseRequest {
    abstract public function execute(): Result;
}