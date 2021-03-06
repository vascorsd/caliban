package caliban

import io.circe._
import zio.test.Assertion._
import zio.test.environment.TestEnvironment
import zio.test._

object GraphQLRequestSpec extends DefaultRunnableSpec {

  override def spec: ZSpec[TestEnvironment, Any] =
    suite("GraphQLRequestSpec")(
      test("can be parsed from JSON") {
        val request = Json
          .obj("query" -> Json.fromString("{}"), "operationName" -> Json.fromString("op"), "variables" -> Json.obj())
        assert(request.as[GraphQLRequest])(
          isRight(
            equalTo(GraphQLRequest(query = "{}", operationName = Some("op"), variables = Some(Map.empty)))
          )
        )
      }
    )
}
