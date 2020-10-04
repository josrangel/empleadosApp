package com.inovacionaplicada.remote;

import com.inovacionaplicada.Constantes;

public class ApiUtils {
    private ApiUtils() {}

    public static ApiService getAPIService() {
        return RetrofitCliente.getClient(Constantes.urlBase).create(ApiService.class);
    }
}
