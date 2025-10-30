   function formatAmount(input) {
       let value = parseFloat(input.value);
       if (!isNaN(value)) {
           input.value = value.toFixed(2);
       } else {
           input.value = 0.00;
       }
   }
