package br.core.audit;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditLogger {

   private static final Logger logger = LoggerFactory.getLogger(AuditLogger.class);

   public void logOperation(String operation, String details) {
      String transactionId = UUID.randomUUID().toString();
      LocalDateTime timestamp = LocalDateTime.now();

      logger.info("Transaction ID: {}, Timestamp: {}, Operation: {}, Details: {}",
            transactionId, timestamp, operation, details);
   }
}
