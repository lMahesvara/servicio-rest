export const getProductos = async (_, res) => {
  const response = await fetch(
    'http://localhost:8080/Restapi2/webresources/producto'
  )
  const data = await response.json()
  return res.status(200).json(data)
}

export const addProducto = async (req, res) => {
  const { nombre, descripcion, precio } = req.body

  const response = await fetch(
    'http://localhost:8080/Restapi2/webresources/producto',
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        nombre,
        descripcion,
        precio,
      }),
    }
  )

  const data = await response.json()

  return res.status(200).json(data)
}

export const updateProducto = async (req, res) => {
  const { id, nombre, descripcion, precio } = req.body

  const response = await fetch(
    `http://localhost:8080/Restapi2/webresources/producto/${id}`,
    {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        id,
        nombre,
        descripcion,
        precio,
      }),
    }
  )

  console.log(response)

  const data = await response.json()

  return res.status(200).json(data)
}

export const deleteProducto = async (req, res) => {
  const { id } = req.params
  console.log('id', id)

  const response = await fetch(
    `http://localhost:8080/Restapi2/webresources/producto/${id}`,
    {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
    }
  )

  if (!response.ok) {
    return res.status(400).json({ message: 'Producto no encontrado' })
  }

  return res.status(200).json({ message: 'Producto eliminado' })
}
