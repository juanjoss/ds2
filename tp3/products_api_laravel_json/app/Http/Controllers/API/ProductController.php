<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Product;
use App\Models\Supplier;
use App\Models\ProductType;
use App\Models\Brand;
use Illuminate\Http\Request;
use App\Http\Requests\ProductRequest;
use App\Http\Resources\ProductResource;
use App\Http\Resources\ProductTypeResource;
use App\Http\Resources\BrandResource;
use App\Http\Resources\SupplierResource;

class ProductController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return ProductResource::collection(Product::all());
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(ProductRequest $request)
    {
        $request->validated();

        $product = new Product();
        $product->id_brand = $request->input('id_brand');
        $product->id_type = $request->input('id_type');
        $product->id_supplier = $request->input('id_supplier');
        $product->name = $request->input('name');
        $product->bar_code = $request->input('bar_code');
        $product->price = $request->input('price');

        $res = $product->save();

        if ($res) {
            $productRes = new ProductResource($product);
            return response()->json(['data' => $productRes], 201);
        }
        return response()->json(['message' => 'Error creating product'], 500);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Product  $product
     * @return \Illuminate\Http\Response
     */
    public function show(Product $product)
    {
        return new ProductResource($product);
    }

    /**
     * Display the specified resource from a relationship.
     *
     * @param  string  $productType
     * @return \Illuminate\Http\Response
     */
    public function showProductType(Product $product, String $productType)
    {
        return new ProductTypeResource(ProductType::find($productType));
    }

    /**
     * Display the specified resource from a relationship.
     *
     * @param  \App\Models\Brand  $brand
     * @return \Illuminate\Http\Response
     */
    public function showBrand(Product $product, String $brand)
    {
        return new BrandResource(Brand::find($brand));
    }

    /**
     * Display the specified resource from a relationship.
     *
     * @param  string  $supplier
     * @return \Illuminate\Http\Response
     */
    public function showSupplier(Product $product, String $supplier)
    {
        return new SupplierResource(Supplier::find($supplier));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Product  $product
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, String $product)
    {
        $product = Product::find($product);
        if($product){
            if (!empty($request->input('id_brand'))) {
                $brand = Brand::find($request->input('id_brand'));
                if($brand){
                    $product->id_brand = $request->input('id_brand');
                }else{
                    return response(null,404);
                }
            }
            if (!empty($request->input('id_type'))) {
                $type = ProductType::find($request->input('id_type'));
                if($type){
                    $product->id_type = $request->input('id_type');
                }else{
                    return response(null,404);
                }
            }
            if (!empty($request->input('id_supplier'))) {
                $supplier = Supplier::find($request->input('id_supplier'));
                if($supplier){
                    $product->id_supplier = $request->input('id_supplier');
                }else{
                    return response(null,404);
                }   
            }
            if (!empty($request->input('name'))) {
                $product->name = $request->input('name');
            }
            if (!empty($request->input('bar_code'))) {
                $product->bar_code = $request->input('bar_code');
            }
            if (!empty($request->input('price'))) {
                $product->price = $request->input('price');
            }
    
            $res = $product->save();
    
            if(count($request->all()) == 0){
                return response()->noContent();
            }
    
            if ($res) {
                return new ProductResource($product);
            }
        }else{
            return response(null,404);
        }

        return response()->json(['message' => 'Error to update product'], 500);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Product  $product
     * @return \Illuminate\Http\Response
     */
    public function destroy(String $product)
    {
        $product = Product::find($product);

        if($product){
            $res = $product->delete();
            return response(null);
        }else{
            return response(null,404);
        }

        return response()->json(['message' => 'Error to delete product'], 500);
    }
}
