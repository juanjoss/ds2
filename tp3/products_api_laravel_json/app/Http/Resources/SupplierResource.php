<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class SupplierResource extends JsonResource
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
            'type' => 'suppliers',
            'id' => $this->id,
            'attributes' => [
                'name' => $this->name,
            ],
            'links' => [
                'self' => route('suppliers.show',$this->id)
            ]
        ];
    }
}
