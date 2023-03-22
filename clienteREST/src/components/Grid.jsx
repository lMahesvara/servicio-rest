import React from 'react'
import axios from 'axios'
import styled from 'styled-components'
import { FaTrash, FaEdit } from 'react-icons/fa'
import { toast } from 'react-toastify'

const Table = styled.table`
  width: 100%;
  background-color: #fff;
  padding: 20px;
  box-shadow: 0px 0px 5px #ccc;
  border-radius: 5px;
  max-width: 1120px;
  margin: 20px auto;
  word-break: break-all;
`

export const Thead = styled.thead``

export const Tbody = styled.tbody``

export const Tr = styled.tr``

export const Th = styled.th`
  text-align: start;
  border-bottom: inset;
  padding-bottom: 5px;

  @media (max-width: 500px) {
    ${props => props.onlyWeb && 'display: none'}
  }
`

export const Td = styled.td`
  padding-top: 15px;
  text-align: ${props => (props.alignCenter ? 'center' : 'start')};
  width: ${props => (props.width ? props.width : 'auto')};

  @media (max-width: 500px) {
    ${props => props.onlyWeb && 'display: none'}
  }
`

const Grid = ({ productos, setProductos, setOnEdit }) => {
  const handleEdit = item => {
    setOnEdit(item)
  }

  const handleDelete = async id => {
    await axios
      .delete('http://localhost:8800/' + id)
      .then(() => {
        const newArray = productos.filter(user => user.id !== id)

        setProductos(newArray)
        toast.success('Producto eliminado con éxito!')
      })
      .catch(() => toast.error('Error al eliminar el producto'))

    setOnEdit(null)
  }

  return (
    <Table>
      <Thead>
        <Tr>
          <Th>Nombre</Th>
          <Th>Descripción</Th>
          <Th>Precio</Th>
          <Th></Th>
          <Th></Th>
        </Tr>
      </Thead>
      <Tbody>
        {productos.map((item, i) => (
          <Tr key={i}>
            <Td width='30%'>{item.nombre}</Td>
            <Td width='30%'>{item.descripcion}</Td>
            <Td width='20%'>{item.precio}</Td>
            <Td alignCenter width='5%'>
              <FaEdit onClick={() => handleEdit(item)} />
            </Td>
            <Td alignCenter width='5%'>
              <FaTrash onClick={() => handleDelete(item.id)} />
            </Td>
          </Tr>
        ))}
      </Tbody>
    </Table>
  )
}

export default Grid
