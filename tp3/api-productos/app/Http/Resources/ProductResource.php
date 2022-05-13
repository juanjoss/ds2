<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class ProductResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array|\Illuminate\Contracts\Support\Arrayable|\JsonSerializable
     */
    public function toArray($request)
    {
        return [
            'id' => $this->id,
            'brand' => [
                'id' => $this->brand->id,
                'name' => $this->brand->name,
            ],
            'type' => [
                'id' => $this->productType->id,
                'name' => $this->productType->name,
            ],
            'supplier' => [
                'id' => $this->supplier->id,
                'name' => $this->supplier->name,
            ],
            'name' => $this->name,
            'bar_code' => $this->bar_code,
            'price' => $this->price,
        ];
    }
}
