# $TOKEN = "e8db177e792b54619e8f8b39fa4fc1d152ef810acd1b5ccc2d95cbbf334a6fc1e3d53ec41cb5666a0a8b043bf9e2326f"

$HEADERS = @{
    # "Authorization" = "Bearer $TOKEN"
    "Content-Type" = "application/json"
    "Api-Token"    = "test-token"
}

$URI = "http://localhost:9000/api/v1/users"

$JSON = @{
    "tenant" = @{
        "id"   = "d3b1cac3-1a0b-4bc3-b1f9-ef7e23dda0af"
        “host" = "https://hrlink.com:8089/api/hosr" 
    }
    "users"  = @(
        @{
            "id"           = "f007f4df-8f6a-4bb7-9e7a-09adfcac1144" 
            "fioduldrHash" = "9fc42df807e8bd5f2af33ad4e33b85921f2a654f5754aaccf535e2c18f8e8aef"
        }
        @{
            "id"           = "82cc58c1-c6dc-46f5-85c3-8cf49d0df684" 
            "fioduldrHash" = "9fc42df807e8bd5f2af33ad4e33b85921f2a654f5754aaccf535e2c18f8e8aef"
        }
        @{
            "id"           = "54655f35-16ef-489e-9bb2-805bd60c29ad"
            "fioduldrHash" = "9fc42df807e8bd5f2af33ad4e33b85921f2a654f5754aaccf535e2c18f8e8aef"
        }
    )
} | ConvertTo-Json

Invoke-WebRequest -Uri $URI -Method POST -Headers $HEADERS -Body $JSON