package dao

import javax.inject.Inject

import play.api.db.{Database, NamedDatabase}

import scala.concurrent.{ExecutionContext, Future}

trait ChannelPartnerDao {
  def getNoOfLeads():Future[String]
}

class ChannelPartnerDaoImpl@Inject()(@NamedDatabase("channelPartner") db:Database)(implicit exec: ExecutionContext) extends ChannelPartnerDao{
  override def getNoOfLeads(): Future[String] = {
      Future{
        db.withConnection { con =>
          val rs = con.prepareStatement("select count(*) from unified_lead_event").executeQuery()
          rs.next()
          rs.getString("count")
        }
      }
  }
}
