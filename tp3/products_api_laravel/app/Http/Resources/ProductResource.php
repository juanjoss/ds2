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
            'brand' => route('productBrand',['product'=>$this->id, 'brand'=>$this->brand->id]),
            'type' => route('productProductType',['product'=>$this->id, 'productType'=>$this->productType->id]),
            'supplier' => route('productSupplier',['product'=>$this->id, 'supplier'=>$this->supplier->id]),
            'name' => $this->name,
            'bar_code' => $this->bar_code,
            'price' => $this->price,
        ];
    }
}
