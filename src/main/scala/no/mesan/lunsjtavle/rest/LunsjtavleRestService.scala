package no.mesan.lunsjtavle.rest

import java.sql.Timestamp
import java.time.{ZoneId, LocalDate}

trait LunsjtavleRestService {
  def convertStringToTimestamp(startDateString: String): Timestamp = {
    Timestamp.from(LocalDate.parse(startDateString).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant)
  }
}
