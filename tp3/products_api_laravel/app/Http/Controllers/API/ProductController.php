<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Product;
use Illuminate\Http\Request;
use App\Http\Requests\ProductRequest;
use App\Http\Resources\ProductResource;

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
     *     @OA\Response(
     *         response=200,
     *         description="success",
     *         @OA\JsonContent(
     *             @OA\Property(
     *                 type="array",
     *                 property="data",
     *                 @OA\Items(
     *                     type="object",
     *                      @OA\Property(
     *                          property="id_brand",
     *                          type="integer",
     *                          example="1"
     *                      ),
     *                      @OA\Property(
     *                          property="id_type",
     *                          type="integer",
     *                          example="2"
     *                      ),
     *                      @OA\Property(
     *                          property="id_supplier",
     *                          type="integer",
     *                          example="3"
     *                      ),
     *                      @OA\Property(
     *                          property="name",
     *                          type="string",
     *                          example="Product name"
     *                      ),
     *                      @OA\Property(
     *                          property="bar_code",
     *                          type="integer",
     *                          example="123456789"
     *                      ),
     *                      @OA\Property(
     *                          property="price",
     *                          type="double",
     *                          example="100.00"
     *                      ),
     *                )
     *             )
     *         )
     *     )
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
     *         @OA\MediaType(
     *             mediaType="application/json",
     *             @OA\Schema(
     *                 @OA\Property(
     *                      type="object",
     *                      @OA\Property(
     *                          property="id_brand",
     *                          type="integer"
     *                      ),
     *                      @OA\Property(
     *                          property="id_type",
     *                          type="integer"
     *                      ),
     *                      @OA\Property(
     *                          property="id_supplier",
     *                          type="integer"
     *                      ),
     *                      @OA\Property(
     *                          property="name",
     *                          type="string"
     *                      ),
     *                      @OA\Property(
     *                          property="barcode",
     *                          type="integer"
     *                      ),
     *                      @OA\Property(
     *                          property="price",
     *                          type="double"
     *                      ),
     *                 ),
     *                 example={
     *                     "id_brand": 1,
     *                     "id_type": 2,
     *                     "id_supplier": 3,
     *                     "name": "product name",
     *                     "bar_code": 001155449,
     *                     "price": 100.00
     *                }
     *             )
     *         )
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
     *         response=200,
     *         description="success",
     *         @OA\JsonContent(
     *            @OA\Property(property="data", type="object",
     *              @OA\Property(property="id_brand", type="integer", example=1),
     *              @OA\Property(property="id_type", type="integer", example=2),
     *              @OA\Property(property="id_supplier", type="integer", example=3),
     *              @OA\Property(property="name", type="string", example="product name"),
     *              @OA\Property(property="bar_code", type="integer", example="123456789"),
     *              @OA\Property(property="price", type="double", example="100.00"),
     *         )
     *       )
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
     *         @OA\MediaType(
     *             mediaType="application/json",
     *             @OA\Schema(
     *                 @OA\Property(
     *                      type="object",
     *                      @OA\Property(
     *                          property="id_brand",
     *                          type="integer"
     *                      ),
     *                      @OA\Property(
     *                          property="id_type",
     *                          type="integer"
     *                      ),
     *                      @OA\Property(
     *                          property="id_supplier",
     *                          type="integer"
     *                      ),
     *                      @OA\Property(
     *                          property="name",
     *                          type="string"
     *                      ),
     *                      @OA\Property(
     *                          property="barcode",
     *                          type="integer"
     *                      ),
     *                      @OA\Property(
     *                          property="price",
     *                          type="double"
     *                      ),
     *                 ),
     *                 example={
     *                     "id_brand": 1,
     *                     "id_type": 2,
     *                     "id_supplier": 3,
     *                     "name": "product name",
     *                     "bar_code": 001155449,
     *                     "price": 100.00
     *                }
     *             )
     *         )
     *      ),
     *     @OA\Response(
     *         response=200,
     *         description="success"
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
