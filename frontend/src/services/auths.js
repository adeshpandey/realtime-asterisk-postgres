import api from "./api";

export function getAuths(token){
    return api.get('auths',{headers: {
        Authorization: `Bearer ${token}`
    }})
}

export function deleteAuth(id, token){
    return api.delete(`auths/${id}`,{headers: {
        Authorization: `Bearer ${token}`
    }})
}

export function addAuth(data, token){
    return api.post(`auths`, data, {headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`
    }})
}

export function updateAuth(id, data, token){
    return api.put(`auths/${id}`, data, {headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`
    }})
}