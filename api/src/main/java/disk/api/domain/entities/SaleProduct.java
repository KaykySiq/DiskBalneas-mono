package disk.api.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sales_products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
  @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", initialValue = 1, allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "sale_id")
  private Sale sale;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = true)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "combo_id", nullable = true)
  private Combo combo;

  private Integer quantity;

  public SaleProduct(Sale sale, Combo combo, Integer quantity) {
    this.sale = sale;
    this.combo = combo;
    this.quantity = quantity;
}

  public SaleProduct(Sale sale, Product product, Integer quantity) {
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
    }
}