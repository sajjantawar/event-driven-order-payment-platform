package com.sajjantawar.payment.domain;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
class PaymentTest {
 @Test void paymentMovesToCompleted(){Payment p=new Payment(UUID.randomUUID(),UUID.randomUUID(),BigDecimal.TEN,"USD","k");p.process();assertThat(p.getStatus()).isEqualTo(PaymentStatus.COMPLETED);}
 @Test void completedPaymentCannotBeProcessedAgain(){Payment p=new Payment(UUID.randomUUID(),UUID.randomUUID(),BigDecimal.TEN,"USD","k");p.process();assertThatThrownBy(p::process).isInstanceOf(IllegalStateException.class);}
}