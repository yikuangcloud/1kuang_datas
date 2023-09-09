import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'shop/express',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'shop/express/' + id,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'shop/express',
    method: 'put',
    data
  })
}

