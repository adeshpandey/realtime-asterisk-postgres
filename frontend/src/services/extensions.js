import api from "./api";

export function getExtensions(token){
    return api.get('extensions',{headers: {
        Authorization: `Bearer ${token}`
    }})
}

export function deleteExtension(id, token){
    return api.delete(`extensions/${id}`,{headers: {
        Authorization: `Bearer ${token}`
    }})
}

export function addExtension(data, token){
    return api.post(`extensions`, data, {headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`
    }})
}

export function updateExtension(id, data, token){
    return api.put(`extensions/${id}`, data, {headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`
    }})
}