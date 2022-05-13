<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ProductSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('products')->insert(
            [
                'id_brand' => 4,
                'id_type' => 2,
                'id_supplier' => 2,
                'name' => 'Yogur Clásico con Frutas',
                'bar_code' => 123456789,
                'price' => 88.00
            ]
        );

        DB::table('products')->insert(
            [
                'id_brand' => 6,
                'id_type' => 3,
                'id_supplier' => 3,
                'name' => 'Jabón líquido para manos',
                'bar_code' => 111111111,
                'price' => 483.00
            ],
        );

        DB::table('products')->insert(
            [
                'id_brand' => 1,
                'id_type' => 1,
                'id_supplier' => 1,
                'name' => 'Samsung A03',
                'bar_code' => 987654321,
                'price' => 34999.99
            ]
        );
    }
}
