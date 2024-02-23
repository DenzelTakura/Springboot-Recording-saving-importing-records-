# Springboot-Recording-saving-importing-records-

Another small project showing how to save records using java and springboot.
Also applies intergration of systems by importiong some data from an external source. code snippet bellow shows how.

'''

    @Override
    public void getPeopleFromSystem() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.32.33:8081/person/all";
        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PersonDto>>() {
                }
        );

        List<PersonDto> personDtos = response.getBody();
        for(PersonDto personDto:personDtos){
            Person person = new Person();
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            person.setIdNumber(personDto.getIdNumber());
            personRepo.save(person);
            System.out.println("collected info for : "+person.getFirstName() +" "+person.getLastName());
        }
    }
    '''
    Be sure to replace"http://192.168.32.33:8081/person/all" with your own endpoint
