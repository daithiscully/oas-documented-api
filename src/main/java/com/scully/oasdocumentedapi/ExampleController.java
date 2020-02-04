package com.scully.oasdocumentedapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

// GET Example ---------------------------------------------------------------------------------------------------------

  private static final String EXAMPLE_RESPONSE_1 = "{\"name\": \"David\", \"address\": \"David's Address\"}";
  private static final String EXAMPLE_RESPONSE_2 = "{\"name\": \"SomeName\", \"address\": \"rasndomValue\"}";
  private static final String EXAMPLE_RESPONSE_3 = "{\n"
      + "  \"userId\": 1,\n"
      + "  \"id\": 1,\n"
      + "  \"title\": \"delectus aut autem\",\n"
      + "  \"completed\": false\n"
      + "}";


  @Operation(
      operationId = "exampleGetEndpoint",
      responses = @ApiResponse(
          description = "Incredible description",
          content = @Content(
              schema = @Schema(implementation = ExampleModel.class),
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(name = "example response #1", value = EXAMPLE_RESPONSE_1),
                  @ExampleObject(name = "example response #2", value = EXAMPLE_RESPONSE_2),
                  @ExampleObject(name = "example (invalid) response #3", value = EXAMPLE_RESPONSE_3)
              }
          )
      )
  )
  @GetMapping
  public ExampleModel exampleGetEndpoint() {
    return ExampleModel.builder()
        .name("David")
        .address("David's Address")
        .build();
  }

// POST example --------------------------------------------------------------------------------------------------------


  private static final String POST_EXAMPLE_1 = "{\"name\": \"Daithi\", \"address\": \"Mo teach\"}";
  private static final String POST_EXAMPLE_2 = "{\"name\": \"David\", \"address\": \"my address\"}";
  private static final String NO_USER_FOUND_ERROR = "{\"errorCode\": \"404\", \"message\": \"could not find a user with that name\"}";


  @Operation(
      operationId = "postExample",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Post Response",
              links = {
                  @Link(name = "get", operationId = "postExample")
              },
              content = @Content(
                  schema = @Schema(implementation = ExampleModel.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  examples = {
                      @ExampleObject(name = "irish example response", value = POST_EXAMPLE_1),
                      @ExampleObject(name = "english example response", value = POST_EXAMPLE_2)
                  })
          ),
          @ApiResponse(
              responseCode = "404",
              description = "User not found response",
              content = @Content(
                  schema = @Schema(implementation = ExampleModel.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  examples = @ExampleObject(name = "Could not find user example response", value = NO_USER_FOUND_ERROR))
          )},
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Post Request",
          required = true,
          content = @Content(
              schema = @Schema(implementation = ExampleModel.class),
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(name = "irish example request", value = POST_EXAMPLE_1),
                  @ExampleObject(name = "english example request", value = POST_EXAMPLE_2)
              }
          )
      )
  )

  @PostMapping
  public ExampleModel examplePostEndpoint(@RequestBody ExampleModel user) {
    return user;
  }


}
