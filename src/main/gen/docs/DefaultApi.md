# DefaultApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**apiSolicitudesGet**](DefaultApi.md#apiSolicitudesGet) | **GET** /api/solicitudes | Listar solicitudes |
| [**apiSolicitudesIdAsignarPut**](DefaultApi.md#apiSolicitudesIdAsignarPut) | **PUT** /api/solicitudes/{id}/asignar | Asignar responsable |
| [**apiSolicitudesIdCerrarPut**](DefaultApi.md#apiSolicitudesIdCerrarPut) | **PUT** /api/solicitudes/{id}/cerrar | Cerrar solicitud |
| [**apiSolicitudesIdClasificarPut**](DefaultApi.md#apiSolicitudesIdClasificarPut) | **PUT** /api/solicitudes/{id}/clasificar | Clasificar solicitud |
| [**apiSolicitudesIdEstadoPatch**](DefaultApi.md#apiSolicitudesIdEstadoPatch) | **PATCH** /api/solicitudes/{id}/estado | Cambiar estado de solicitud |
| [**apiSolicitudesIdGet**](DefaultApi.md#apiSolicitudesIdGet) | **GET** /api/solicitudes/{id} | Obtener solicitud por id |
| [**apiSolicitudesIdPrioridadPatch**](DefaultApi.md#apiSolicitudesIdPrioridadPatch) | **PATCH** /api/solicitudes/{id}/prioridad | Cambiar prioridad de solicitud |
| [**apiSolicitudesPost**](DefaultApi.md#apiSolicitudesPost) | **POST** /api/solicitudes | Crear una solicitud |


<a id="apiSolicitudesGet"></a>
# **apiSolicitudesGet**
> apiSolicitudesGet()

Listar solicitudes

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      apiInstance.apiSolicitudesGet();
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiSolicitudesGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Lista de solicitudes |  -  |

<a id="apiSolicitudesIdAsignarPut"></a>
# **apiSolicitudesIdAsignarPut**
> apiSolicitudesIdAsignarPut(id)

Asignar responsable

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      apiInstance.apiSolicitudesIdAsignarPut(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiSolicitudesIdAsignarPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Responsable asignado |  -  |

<a id="apiSolicitudesIdCerrarPut"></a>
# **apiSolicitudesIdCerrarPut**
> apiSolicitudesIdCerrarPut(id)

Cerrar solicitud

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      apiInstance.apiSolicitudesIdCerrarPut(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiSolicitudesIdCerrarPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Solicitud cerrada |  -  |

<a id="apiSolicitudesIdClasificarPut"></a>
# **apiSolicitudesIdClasificarPut**
> apiSolicitudesIdClasificarPut(id)

Clasificar solicitud

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      apiInstance.apiSolicitudesIdClasificarPut(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiSolicitudesIdClasificarPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Solicitud clasificada |  -  |

<a id="apiSolicitudesIdEstadoPatch"></a>
# **apiSolicitudesIdEstadoPatch**
> apiSolicitudesIdEstadoPatch(id)

Cambiar estado de solicitud

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      apiInstance.apiSolicitudesIdEstadoPatch(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiSolicitudesIdEstadoPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Estado actualizado |  -  |

<a id="apiSolicitudesIdGet"></a>
# **apiSolicitudesIdGet**
> apiSolicitudesIdGet(id)

Obtener solicitud por id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      apiInstance.apiSolicitudesIdGet(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiSolicitudesIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Solicitud encontrada |  -  |

<a id="apiSolicitudesIdPrioridadPatch"></a>
# **apiSolicitudesIdPrioridadPatch**
> apiSolicitudesIdPrioridadPatch(id)

Cambiar prioridad de solicitud

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "id_example"; // String | 
    try {
      apiInstance.apiSolicitudesIdPrioridadPatch(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiSolicitudesIdPrioridadPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Prioridad actualizada |  -  |

<a id="apiSolicitudesPost"></a>
# **apiSolicitudesPost**
> apiSolicitudesPost()

Crear una solicitud

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      apiInstance.apiSolicitudesPost();
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiSolicitudesPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Solicitud creada |  -  |

