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

//Product relationship routes
Route::get('/products/{product}/supplier/{supplier}',[App\Http\Controllers\API\SupplierController::class, 'show_rel'])->name('productSupplier')->middleware('api');
Route::get('/products/{product}/brand/{brand}',[App\Http\Controllers\API\BrandController::class, 'show_rel'])->name('productBrand')->middleware('api');
Route::get('/products/{product}/type/{productType}',[App\Http\Controllers\API\ProductTypeController::class, 'show_rel'])->name('productProductType')->middleware('api');
