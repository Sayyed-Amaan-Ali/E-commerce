function showCart() {
    var modal = document.getElementById("cartModal");
    modal.style.display = "block";
}

document.getElementsByClassName("close")[0].onclick = function() {
    var modal = document.getElementById("cartModal");
    modal.style.display = "none";
}

window.onclick = function(event) {
    var modal = document.getElementById("cartModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
}



document.addEventListener("DOMContentLoaded", function() {
    var dropdownBtn = document.getElementById('dropdownBtn');
    var dropdownContent = document.getElementById('dropdownContent');

    dropdownBtn.addEventListener('click', function() {
        dropdownContent.classList.toggle('show');
    });

    window.addEventListener('click', function(event) {
        if (!dropdownBtn.contains(event.target) && !dropdownContent.contains(event.target)) {
            dropdownContent.classList.remove('show');
        }
    });

    var sortByName = document.getElementById('sortByName');
    var sortByPrice = document.getElementById('sortByPrice');

    sortByName.addEventListener('click', function() {
        // Your logic to sort by name
        console.log('Sorting by name...');
        dropdownContent.classList.remove('show');
    });

    sortByPrice.addEventListener('click', function() {
        // Your logic to sort by price
        console.log('Sorting by price...');
        dropdownContent.classList.remove('show');
    });
});




document.addEventListener('DOMContentLoaded', function() {
  // Brands button click event
  document.getElementById('brandsBtn').addEventListener('click', function() {
    showDropdown('brandsDropdown');
    // Fetch and display brands options
    // Replace with your AJAX call to fetch brands from the database
    var brands = ['Brand 1', 'Brand 2', 'Brand 3'];
    displayOptions('brandsOptions', brands);
  });

  // Description button click event
  document.getElementById('descriptionBtn').addEventListener('click', function() {
    showDropdown('descriptionDropdown');
    // Fetch and display description options
    // Replace with your AJAX call to fetch descriptions from the database
    var descriptions = ['Description 1', 'Description 2', 'Description 3'];
    displayOptions('descriptionOptions', descriptions);
  });

  // Price button click event
  document.getElementById('priceBtn').addEventListener('click', function() {
    showDropdown('priceDropdown');
  });

  // Apply button click event
  document.getElementById('applyBtn').addEventListener('click', function() {
    // Get selected options and price range
    var selectedBrands = getSelectedOptions('brandsOptions');
    var selectedDescriptions = getSelectedOptions('descriptionOptions');
    var startPrice = document.getElementById('startPrice').value;
    var endPrice = document.getElementById('endPrice').value;
    // Send the filter criteria to display.jsp or handle them as needed
    console.log('Selected Brands:', selectedBrands);
    console.log('Selected Descriptions:', selectedDescriptions);
    console.log('Price Range:', startPrice, '-', endPrice);
    // Redirect to display.jsp or perform other actions
    // window.location.href = 'display.jsp?brands=' + selectedBrands + '&descriptions=' + selectedDescriptions + '&startPrice=' + startPrice + '&endPrice=' + endPrice;
  });
});


// Function to display dropdown menu
function showDropdown(dropdownId) {
  var dropdowns = document.getElementsByClassName('filter-dropdown');
  for (var i = 0; i < dropdowns.length; i++) {
    dropdowns[i].style.display = 'none';
  }
  document.getElementById(dropdownId).style.display = 'block';
}

