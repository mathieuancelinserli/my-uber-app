package controllers

import org.reactivecouchbase.webstack.actions.Action
import org.reactivecouchbase.webstack.env.Env
import org.reactivecouchbase.webstack.result.Results._
import play.api.libs.json._

object HomeController {

  implicit val ec  = Env.defaultExecutionContext
  implicit val mat = Env.defaultMaterializer

  def index = Action.sync { ctx =>
    val appId = Option(System.getenv("APP_ID")).getOrElse("none")
    val commitId = Option(System.getenv("COMMIT_ID")).getOrElse("none")
    val instanceNumber = Option(System.getenv("INSTANCE_NUMBER")).getOrElse("none")
    Ok.json(Json.prettyPrint(Json.obj(
      "scheme" -> ctx.scheme,
      "port" -> ctx.port,
      "host" -> ctx.hostName,
      "hostAndPort" -> ctx.hostAndPort,
      "uri" -> ctx.uri,
      "sourceAddress" -> ctx.sourceAddress.toString(),
      "queryString" -> ctx.queryString,
      "localAddress" -> java.net.InetAddress.getLocalHost().getHostAddress(),
      "appId" -> appId,
      "commitId" -> commitId,
      "instanceNumber" -> instanceNumber
    )))
  }
}
