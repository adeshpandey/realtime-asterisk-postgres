import api from "./api";

export function getAors(token){
    return api.get('aors',{headers: {
        Authorization: `Bearer ${token}`
    }})
}

export function deleteAor(id, token){
    return api.delete(`aors/${id}`,{headers: {
        Authorization: `Bearer ${token}`
    }})
}

export function addAor(data, token){
    return api.post(`aors`, data, {headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`
    }})
}

export function updateAor(id, data, token){
    return api.put(`aors/${id}`, data, {headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`
    }})
}