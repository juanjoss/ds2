<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Product;
use Illuminate\Http\Request;
use App\Http\Requests\ProductRequest;
use App\Http\Resources\ProductResource;

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
            return response()->json(['message' => 'Product created successfully'], 201);
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
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Product  $product
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Product $product)
    {
        if (!empty($request->input('id_brand'))) {
            $product->id_brand = $request->input('id_brand');
        }
        if (!empty($request->input('id_type'))) {
            $product->id_type = $request->input('id_type');
        }
        if (!empty($request->input('id_supplier'))) {
            $product->id_supplier = $request->input('id_supplier');
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

        if ($res) {
            return response()->json(['message' => 'Product update successfully', 'product' => $product], 201);
        }
        return response()->json(['message' => 'Error to update product'], 500);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Product  $product
     * @return \Illuminate\Http\Response
     */
    public function destroy(Product $product)
    {
        $res = $product->delete();

        if ($res) {
            return response()->json(['message' => 'Product delete succesfully']);
        }

        return response()->json(['message' => 'Error to delete product'], 500);
    }
}
