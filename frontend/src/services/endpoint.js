import api from "./api";

export function getEndpoints(token){
    return api.get('endpoints',{headers: {
        Authorization: `Bearer ${token}`
    }})
}

export function deleteEndpoint(id, token){
    return api.delete(`endpoints/${id}`,{headers: {
        Authorization: `Bearer ${token}`
    }})
}

export function addEndpoint(data, token){
    return api.post(`endpoints`, data, {headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`
    }})
}

export function updateEndpoint(id, data, token){
    return api.put(`endpoints/${id}`, data, {headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`
    }})
}