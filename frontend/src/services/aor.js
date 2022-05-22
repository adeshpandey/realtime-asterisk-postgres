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