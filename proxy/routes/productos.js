import express from 'express'
import {
  addProducto,
  deleteProducto,
  getProductos,
  updateProducto,
} from '../controllers/productos.js'

const router = express.Router()

router.get('/', getProductos)

router.post('/', addProducto)

router.put('/', updateProducto)

router.delete('/:id', deleteProducto)

export default router
