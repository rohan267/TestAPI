

$(document).ready( function () {
//	 var table = $('#herosTable').DataTable({
//			"sAjaxSource": "https://localhost:8080/getList",
//			"sAjaxDataProp": "",
//			"order": [[ 0, "asc" ]],
//			"aoColumns": [
//			    { "mData": "id"},
//			    { "mData": "name" },
//				  { "mData": "health" },
//				  { "mData": "age" },
//				  { "mData": "height" },
//				  { "mData": "difficulty" }
//			]
//	 })
//	alert("welcome");
	$('#heroByIdDiv').hide();
	$('#herosListTableDiv').hide();
	$('#abilitiesListTableDiv').hide();
});
//}

function renderHerosListTable() {
	var $table = $('#herosTable');
	$('#herosListTableDiv').show();
	$('#heroByIdDiv').hide();
	$('#abilitiesTable').hide();
	$('#abilityByIdDiv').hide();

//	$('#tryDiv').html('');
	if($table.length) {
		//console.log("inside table");
		
		var jsonURL = jsonURL="http://localhost:8080/getList";
		
		$table.DataTable({
			
			ajax :{
				url:jsonURL,
				dataSrc:''
			},
			columns: [
				{
					data:'id',
				},
				{
					data:'name'
				},
				{
					data:'description'
				},
				{
					data:'health'
				},
				{
					data:'armour'
				},
				{
					data:'shield'
				},
				{
					data:'real_name'
				},
				{
					data:'age'
				},
				{
					data:'height'
				},
				{
					data:'affiliation'
				},
				{
					data:'base_of_operations'
				},
				{
					data:'difficulty'
				},
				{
					data:'url'
				}
			]
		});
//		$('#tryDiv').append($table);
	}
}
	
// Render Hero by Id 
function getHeroById() {
	
	$('#herosListTableDiv').hide();
	$('#abilitiesTable').hide();
	$('#heroByIdDiv').show();
	$('#abilityByIdDiv').hide();
	
	var id=$('#heroId').val();
	if(id==null || id=='') {
		alert("Hero ID cannot be blank");
		return;
	}
	
	$.ajax({
		url: "http://localhost:8080/getHeroById/"+id
    }).then(function(data) {
       $('#heroId').html(data.id);
       $('#heroName').html(data.name);
       $('#heroDescription').html(data.description);
       $('#heroHealth').html(data.health);
       $('#heroArmour').html(data.armour);
       $('#heroShield').html(data.shield);
       $('#heroRealName').html(data.real_name);
       $('#heroAge').html(data.age);
       $('#heroHeight').html(data.height);
       $('#heroAffiliation').html(data.affiliation);
       $('#heroBaseOperation').html(data.base_of_operations);
       $('#heroDifficulty').html(data.difficulty);
       $('#heroUrl').html(data.url);
    });
	
//	$('#heroByIdDiv').show();
}


// Render Abilites list
function renderAbilityList() {
	var $table = $('#abilitiesTable');
	$('#abilitiesListTableDiv').show();
	$('#herosListTableDiv').hide();
	$('#heroByIdDiv').hide();
	$('#abilityByIdDiv').hide();
	
//	$('#tryDiv').html('');
		//console.log("inside table");
		
		var jsonURL = jsonURL="http://localhost:8080/getAbilities";
		
		$table.DataTable({
			
			ajax :{
				url:jsonURL,
				dataSrc:''
			},
			columns: [
				{
					data:'id',
				},
				{
					data:'name'
				},
				{
					data:'description'
				},
				{
					data:'is_ultimate'
				},
				{
					data:'url'
				},
				{
					data:'hero_id'
				}
			]
		});
		
//		$('#tryDiv').html($table);
}


// Ability by Id
function renderAbilityById() {
	alert(abilityId);
	$('#abilityByIdDiv').show();
	$('#herosListTableDiv').hide();
	$('#abilitiesTable').hide();
	$('#heroByIdDiv').hide();
	
	var id=$('#abilityId').val();
	if(id==null || id=='') {
		alert("Ability ID cannot be blank");
		return;
	}
	
	$.ajax({
		url: "http://localhost:8080/getAbilityById/"+id
    }).then(function(data) {
    	
    		console.log(data.hero_id);
    		console.log(data.id);
    	
       $('#abilityId').html(data.id);
       $('#abilityName').html(data.name);
       $('#abilityDescription').html(data.description);
       $('#abilityIsUltimate').html(data.is_ultimate);
       $('#abilityUrl').html(data.url);
       $('#abilityHeroId').html(data.heroid);
    });
	
//	$('#heroByIdDiv').show();
}


function renderHeroAbilityById() {
	var heroId=$('#heroComboId').val();
	var abilityId=$('#abilityComboId').val();
	
	if(heroId=='' || abilityId=='') {
		alert("Hero ID and Ability ID both are required");
		return;
	}
	
	getHeroById($('#heroComboId').val());
	renderAbilityById($('#abilityComboId').val());
	
}