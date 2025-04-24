document.querySelector(".list-group").addEventListener("click", (e) => {
  const chk = e.target;
  console.log(chk);
  console.log(chk.checked);

  const id = chk.closest("label").dataset.id;
  console.log(id);
  const actionForm = document.querySelector("#actionForm");

  actionForm.id.value = id;
  actionForm.completed.value = chk.checked;

  actionForm.submit();
});
