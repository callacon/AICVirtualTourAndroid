package com.example.aicvirtualtour

import com.example.aicvirtualtour.models.Artwork
import com.example.aicvirtualtour.models.ArtworkId
import com.example.aicvirtualtour.models.Department

val mockDepartments: List<Department> = listOf(
    Department("1", "Department #1"),
    Department("2", "Department #2"),
    Department("3", "Department #3"),
    Department("4", "Department #4"),
    Department("5", "Department #5"),
    Department("6", "Department #6")
)

val mockArtworkIds: List<ArtworkId> = listOf(
    ArtworkId(1, "Art 1"),
    ArtworkId(2, "Art 2"),
    ArtworkId(3, "Art 3"),
    ArtworkId(4, "Art 4"),
    ArtworkId(5, "Art 5"),
    ArtworkId(6, "Art 6"),
    ArtworkId(7, "Art 7"),
    ArtworkId(8, "Art 8"),
    ArtworkId(9, "Art 9"),
    ArtworkId(10, "Art 10")
)

val mockArtworks: List<Artwork> = listOf(
    Artwork("1", "1", "The Starry Night", "June 1889", "Vincent Van Gogh", "Oil paint", "2'5\" x 3'0\""),
    Artwork("2", "1", "Mona Lisa", "1503", "Leonardo da Vinci", "Oil on poplar panel", "2'6\" x 1'9\""),
    Artwork("3", "1", "Guernica", "April 26, 1937 - June 1937", "Pablo Picasso", "Oil on canvas", "11'6\" x 25'6\""),
)

val departmentResponseJson = "{\n" +
        "    \"pagination\": {\n" +
        "        \"total\": 15,\n" +
        "        \"limit\": 12,\n" +
        "        \"offset\": 0,\n" +
        "        \"total_pages\": 2,\n" +
        "        \"current_page\": 1,\n" +
        "        \"next_url\": \"https://api.artic.edu/api/v1/departments?page=2\"\n" +
        "    },\n" +
        "    \"data\": [\n" +
        "        {\n" +
        "            \"id\": \"PC-1\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-1\",\n" +
        "            \"title\": \"Arts of Africa\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Arts of Africa\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2020-09-15T12:08:47-05:00\",\n" +
        "            \"last_updated\": \"2020-09-15T14:20:38-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-826\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-826\",\n" +
        "            \"title\": \"AIC Archives\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"AIC Archives\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2020-09-12T11:53:31-05:00\",\n" +
        "            \"last_updated\": \"2020-09-12T12:44:10-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-824\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-824\",\n" +
        "            \"title\": \"Ryerson and Burnham Libraries Special Collections\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Ryerson and Burnham Libraries Special Collections\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2020-09-12T11:53:59-05:00\",\n" +
        "            \"last_updated\": \"2020-09-12T12:44:07-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-7\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-7\",\n" +
        "            \"title\": \"Arts of Asia\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Arts of Asia\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2020-09-12T11:54:48-05:00\",\n" +
        "            \"last_updated\": \"2020-09-12T12:33:42-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-10\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-10\",\n" +
        "            \"title\": \"Painting and Sculpture of Europe\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Painting and Sculpture of Europe\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2020-09-12T11:56:38-05:00\",\n" +
        "            \"last_updated\": \"2020-09-12T12:33:10-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-9\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-9\",\n" +
        "            \"title\": \"Applied Arts of Europe\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Applied Arts of Europe\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2020-09-12T11:57:25-05:00\",\n" +
        "            \"last_updated\": \"2020-09-12T12:33:04-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-4\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-4\",\n" +
        "            \"title\": \"Arts of the Ancient Mediterranean and Byzantium\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Arts of the Ancient Mediterranean and Byzantium\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2020-09-12T12:00:41-05:00\",\n" +
        "            \"last_updated\": \"2020-09-12T12:30:41-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-12\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-12\",\n" +
        "            \"title\": \"Photography and Media\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Photography and Media\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2020-09-12T11:55:25-05:00\",\n" +
        "            \"last_updated\": \"2020-09-12T11:55:38-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-11\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-11\",\n" +
        "            \"title\": \"Modern Art\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Modern Art\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2019-05-08T18:03:55-05:00\",\n" +
        "            \"last_updated\": \"2019-05-09T17:01:10-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:12-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-13\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-13\",\n" +
        "            \"title\": \"Prints and Drawings\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Prints and Drawings\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2019-05-08T18:02:49-05:00\",\n" +
        "            \"last_updated\": \"2019-05-09T17:01:10-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:13-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-135\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-135\",\n" +
        "            \"title\": \"Provenance Research Project\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"last_updated_source\": \"2019-05-08T18:03:43-05:00\",\n" +
        "            \"last_updated\": \"2019-05-09T17:01:10-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:13-05:00\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": \"PC-14\",\n" +
        "            \"api_model\": \"category-terms\",\n" +
        "            \"api_link\": \"https://api.artic.edu/api/v1/category-terms/PC-14\",\n" +
        "            \"title\": \"Textiles\",\n" +
        "            \"subtype\": \"department\",\n" +
        "            \"parent_id\": null,\n" +
        "            \"suggest_autocomplete_all\": {\n" +
        "                \"input\": [\n" +
        "                    \"Textiles\"\n" +
        "                ],\n" +
        "                \"contexts\": {\n" +
        "                    \"groupings\": [\n" +
        "                        \"title\"\n" +
        "                    ]\n" +
        "                }\n" +
        "            },\n" +
        "            \"last_updated_source\": \"2019-05-08T18:03:47-05:00\",\n" +
        "            \"last_updated\": \"2019-05-09T17:01:10-05:00\",\n" +
        "            \"timestamp\": \"2021-04-05T00:17:13-05:00\"\n" +
        "        }\n" +
        "    ],\n" +
        "    \"info\": {\n" +
        "        \"license_text\": \"The data in this response is licensed under a Creative Commons Zero (CC0) 1.0 designation and the Terms and Conditions of artic.edu.\",\n" +
        "        \"license_links\": [\n" +
        "            \"https://creativecommons.org/publicdomain/zero/1.0/\",\n" +
        "            \"https://www.artic.edu/terms\"\n" +
        "        ],\n" +
        "        \"version\": \"1.1\"\n" +
        "    },\n" +
        "    \"config\": {\n" +
        "        \"iiif_url\": \"https://www.artic.edu/iiif/2\",\n" +
        "        \"website_url\": \"http://www.artic.edu\"\n" +
        "    }\n" +
        "}"

