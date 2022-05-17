<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::apiResource('/products',App\Http\Controllers\API\ProductController::class)->middleware('api');
Route::apiResource('/brands',App\Http\Controllers\API\BrandController::class)->middleware('api');
Route::apiResource('/product_types',App\Http\Controllers\API\ProductTypeController::class)->middleware('api');
Route::apiResource('/suppliers',App\Http\Controllers\API\SupplierController::class)->middleware('api');

//Product relationship routes
Route::get('/products/{product}/supplier',[App\Http\Controllers\API\ProductController::class, 'showSupplier'])->name('productSupplier')->middleware('api');
Route::get('/products/{product}/brand',[App\Http\Controllers\API\ProductController::class, 'showBrand'])->name('productBrand')->middleware('api');
Route::get('/products/{product}/type',[App\Http\Controllers\API\ProductController::class, 'showProductType'])->name('productProductType')->middleware('api');
