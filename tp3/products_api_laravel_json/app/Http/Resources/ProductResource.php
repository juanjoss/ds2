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
            'type' => 'products',
            'id' => $this->id,
            'attributes' => [
                'name' => $this->name,
                'bar_code' => $this->bar_code,
                'price' => $this->price,
            ],
            'relationships' => [
                'brand' => [
                    'links' => [
                        'related' => route('productBrand',['product'=>$this->id])
                    ],
                    'data' => [
                        'type' => 'brands',
                        'id' => $this->brand->id
                    ]
                ],
                'type' => [
                    'links' => [
                        'related' => route('productProductType',['product'=>$this->id])
                    ],
                    'data' => [
                        'type' => 'productTypes',
                        'id' => $this->productType->id
                    ]
                ],
                'supplier' => [
                    'links' => [
                        'related' => route('productSupplier',['product'=>$this->id])
                    ],
                    'data' => [
                        'type' => 'suppliers',
                        'id' => $this->supplier->id
                    ]
                ],
            ],
            'links' => [
                'self' => route('products.show', ['product'=>$this])
            ]
        ];
    }
}
