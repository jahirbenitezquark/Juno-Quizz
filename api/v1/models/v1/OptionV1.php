<?php

class OptionV1 {
    private int $optionId;
    private string $optionText;
    private int $questionId;
    private bool $active;

    public function setOptionId(int $optionId): void {
        $this->optionId = $optionId;
    }

    public function getOptionId(): int {
        return $this->optionId;
    }

    public function setOptionText(string $optionText): void {
        $this->optionText = $optionText;
    }

    public function getOptionText(): string {
        return $this->optionText;
    }

    public function setQuestionId(int $questionId): void {
        $this->questionId = $questionId;
    }

    public function getQuestionId(): int {
        return $this->questionId;
    }

    public function setActive(bool $active): void {
        $this->active = $active;
    }

    public function isActive(): bool {
        return $this->active;
    }

    public function toArray(): array {
        return array(
            Constants::optionId => $this->getOptionId(),
            Constants::optionText => $this->getOptionText(),
            Constants::questionId => $this->getQuestionId(),
            Constants::active => $this->isActive()
        );
    }

    public function __toString() {
        $array = [
            Constants::optionId => $this->getOptionId(),
            Constants::optionText => $this->getOptionText(),
            Constants::questionId => $this->getQuestionId(),
            Constants::active => $this->isActive()
        ];

        return json_encode($array);
    }
}