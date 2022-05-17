<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Product;
use App\Models\ProductType;
use App\Models\Brand;
use App\Models\Supplier;
use Illuminate\Http\Request;
use App\Http\Requests\ProductRequest;
use App\Http\Resources\ProductResource;
use App\Http\Resources\ProductTypeResource;
use App\Http\Resources\BrandResource;
use App\Http\Resources\SupplierResource;

/**
 * @OA\OpenApi(
 *     @OA\Info(
 *         version="1.0",
 *         title="Products Laravel API REST",
 *         description="CRUD products table",
 *     )
 * )
 */
class ProductController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    /**
     * @OA\Get(
     *      path="/api/products",
     *      tags={"Products"},
     *      summary="Get list of products",
     *      description="Returns list of products",
     *  @OA\Response(
     *          response=200,
     *          description="Successful operation",
     *          @OA\JsonContent(ref="#/components/schemas/Products")
     *       ),
     * )
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


    /**
     * @OA\Post (
     *     path="/api/products",
     *     tags={"Products"},
     *     summary="Store new project",
     *     description="Create a new product",
     *     @OA\RequestBody(
     *          required=true,
     *          @OA\JsonContent(ref="#/components/schemas/StoreProductRequest")
     *      ),
     *      @OA\Response(
     *          response=201,
     *          description="success",
     *          @OA\JsonContent(
     *              @OA\Property(property="message", type="string", example="Product created successfully"),
     *          )
     *      ),
     *      @OA\Response(
     *          response=500,
     *          description="invalid",
     *          @OA\JsonContent(
     *              @OA\Property(property="message", type="string", example="Error creating product"),
     *          )
     *      )
     * )
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


    /**
     * @OA\Get (
     *     path="/api/products/{id}",
     *     tags={"Products"},
     *     summary="Get product information",
     *     description="Returns product data",
     *     @OA\Parameter(
     *         in="path",
     *         name="id",
     *         required=true,
     *         @OA\Schema(type="string")
     *     ),
     *     @OA\Response(
     *          response=200,
     *          description="Successful operation",
     *          @OA\JsonContent(ref="#/components/schemas/Product")
     *  ),
     *    @OA\Response(
     *    response=404,
     *    description="Product Not found"
     *    )
     * )
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
    public function showProductType(Product $product)
    {
        return new ProductTypeResource($product->productType);
    }

    /**
     * Display the specified resource from a relationship.
     *
     * @param  \App\Models\Brand  $brand
     * @return \Illuminate\Http\Response
     */
    public function showBrand(Product $product)
    {
        return new BrandResource($product->brand);
    }

    /**
     * Display the specified resource from a relationship.
     *
     * @param  string  $supplier
     * @return \Illuminate\Http\Response
     */
    public function showSupplier(Product $product)
    {
        return new SupplierResource($product->supplier);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Product  $product
     * @return \Illuminate\Http\Response
     */


    /**
     * @OA\Patch(
     *      path="/api/products/{id}",
     *      tags={"Products"},
     *      summary="Update existing product",
     *      description="Returns updated product data",
     *     @OA\Parameter(
     *         in="path",
     *         name="id",
     *         required=true,
     *         @OA\Schema(type="string")
     *     ),
     *      @OA\RequestBody(
     *      required=true,
     *          @OA\JsonContent(ref="#/components/schemas/StoreProductRequest")
     *      ),
     *     @OA\Response(
     *         response=200,
     *         description="success",
     *         @OA\JsonContent(ref="#/components/schemas/Product")
     *     ),
     *     @OA\Response(
     *         response=404,
     *         description="Product Not found"
     *    )
     * )
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
            return response()->json(['message' => 'Product update successfully', 'product' => $product]);
        }
        return response()->json(['message' => 'Error to update product'], 500);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Product  $product
     * @return \Illuminate\Http\Response
     */

    /**
     * @OA\Delete (
     *     path="/api/products/{id}",
     *     tags={"Products"},
     *     summary="Delete existing project",
     *     description="Deletes a record and returns no content",
     *     @OA\Parameter(
     *         in="path",
     *         name="id",
     *         required=true,
     *         @OA\Schema(type="string")
     *     ),
     *     @OA\Response(
     *          response=200,
     *          description="success",
     *          @OA\JsonContent(
     *              @OA\Property(property="message", type="string", example="Product deleted successfully"),
     *          )
     *      ),
     *    @OA\Response(
     *    response=404,
     *    description="Product Not found"
     *    ),
     *      @OA\Response(
     *          response=500,
     *          description="invalid",
     *          @OA\JsonContent(
     *              @OA\Property(property="message", type="string", example="Error to delete product"),
     *          )
     *      )
     * )
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
