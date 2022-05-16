<?php

class ProductV2 {
    private int $productId;
    private string $productName;
    private float $productPrice;
    private int $familyId;
    private int $rate;
    private bool $sale;
    private float $oldPrice;
    private string $imageURL;
    private bool $active;

    public function setProductId(int $productId): void {
        $this->productId = $productId;
    }

    public function getProductId(): int {
        return $this->productId;
    }

    public function setProductName(string $productName): void {
        $this->productName = $productName;
    }

    public function getProductName(): string {
        return $this->productName;
    }

    public function setProductPrice(float $productPrice): void {
        $this->productPrice = $productPrice;
    }

    public function getProductPrice(): float {
        return $this->productPrice;
    }

    public function setFamilyId(int $familyId): void {
        $this->familyId = $familyId;
    }

    public function getFamilyId(): int {
        return $this->familyId;
    }

    public function setRate(int $rate): void {
        $this->rate = $rate;
    }

    public function getRate(): int {
        return $this->rate;
    }

    public function setSale(bool $sale): void {
        $this->sale = $sale;
    }

    public function isSale(): bool {
        return $this->sale;
    }

    public function setOldPrice(float $oldPrice): void {
        $this->oldPrice = $oldPrice;
    }

    public function getOldPrice(): float {
        return $this->oldPrice;
    }

    public function setImageURL(string $imageURL): void {
        $this->imageURL = $imageURL;
    }

    public function getImageURL(): string {
        return $this->imageURL;
    }

    public function setActive(bool $active): void {
        $this->active = $active;
    }

    public function isActive(): bool {
        return $this->active;
    }

    public function toArray(): array {
        return array(
            Constants::productId => $this->getProductId(),
            Constants::productName => $this->getProductName(),
            Constants::productPrice => $this->getProductPrice(),
            Constants::familyId => $this->getFamilyId(),
            Constants::rate => $this->getRate(),
            Constants::sale => $this->isSale(),
            Constants::oldPrice => $this->getOldPrice(),
            Constants::imageURL => $this->getImageURL(),
            Constants::active => $this->isActive()
        );
    }

    public function __toString() {
        $array = [
            Constants::productId => $this->getProductId(),
            Constants::productName => $this->getProductName(),
            Constants::productPrice => $this->getProductPrice(),
            Constants::familyId => $this->getFamilyId(),
            Constants::rate => $this->getRate(),
            Constants::sale => $this->isSale(),
            Constants::oldPrice => $this->getOldPrice(),
            Constants::imageURL => $this->getImageURL(),
            Constants::active => $this->isActive()
        ];

        return json_encode($array);
    }
}