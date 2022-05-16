<?php

class QuestionV1 {
    private int $questionId;
    private string $questionText;
    private int $answerId;
    private array $options = [];
    private bool $active;

    public function setQuestionId(int $questionId): void {
        $this->questionId = $questionId;
    }

    public function getQuestionId(): int {
        return $this->questionId;
    }

    public function setQuestionText(string $questionText): void {
        $this->questionText = $questionText;
    }

    public function getQuestionText(): string {
        return $this->questionText;
    }

    public function setAnswerId(int $answerId): void {
        $this->answerId = $answerId;
    }

    public function getAnswerId(): int {
        return $this->answerId;
    }

    public function setOptions(array $options): void {
        $this->options = $options;
    }

    public function getOptions(): array {
        return $this->options;
    }

    public function setActive(bool $active): void {
        $this->active = $active;
    }

    public function isActive(): bool {
        return $this->active;
    }

    public function toArray(): array {
        return array(
            Constants::questionId => $this->getQuestionId(),
            Constants::questionText => $this->getQuestionText(),
            Constants::answerId => $this->getAnswerId(),
            Constants::options => $this->getOptions(),
            Constants::active => $this->isActive()
        );
    }

    public function __toString() {
        $array = [
            Constants::questionId => $this->getQuestionId(),
            Constants::questionText => $this->getQuestionText(),
            Constants::answerId => $this->getAnswerId(),
            Constants::options => $this->getOptions(),
            Constants::active => $this->isActive()
        ];

        return json_encode($array);
    }
}