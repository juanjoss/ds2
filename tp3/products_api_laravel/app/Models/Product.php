<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Product extends Model
{
    use HasFactory;
    public $timestamps = false;

    public function supplier(){
        return $this->belongsTo(Supplier::class,'id_supplier');
    }

    public function brand(){
        return $this->belongsTo(Brand::class,'id_brand');
    }

    public function productType(){
        return $this->belongsTo(ProductType::class,'id_type');
    }
}
