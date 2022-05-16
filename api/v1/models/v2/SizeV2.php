<?php

class SizeV2 {
    private int $sizeId;
    private string $sizeText;
    private int $productId;
    private bool $active;

    public function setSizeId(int $sizeId): void {
        $this->sizeId = $sizeId;
    }

    public function getSizeId(): int {
        return $this->sizeId;
    }

    public function setSizeText(string $sizeText): void {
        $this->sizeText = $sizeText;
    }

    public function getSizeText(): string {
        return $this->sizeText;
    }

    public function setProductId(int $productId): void {
        $this->productId = $productId;
    }

    public function getProductId(): int {
        return $this->productId;
    }

    public function setActive(bool $active): void {
        $this->active = $active;
    }

    public function isActive(): bool {
        return $this->active;
    }

    public function toArray(): array {
        return array(
            Constants::sizeId => $this->getSizeId(),
            Constants::sizeText => $this->getSizeText(),
            Constants::productId => $this->getProductId(),
            Constants::active => $this->isActive()
        );
    }

    public function __toString() {
        $array = [
            Constants::sizeId => $this->getSizeId(),
            Constants::sizeText => $this->getSizeText(),
            Constants::productId => $this->getProductId(),
            Constants::active => $this->isActive()
        ];

        return json_encode($array);
    }
}