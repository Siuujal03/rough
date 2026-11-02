//SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

contract StudentData
{
    struct Student
    {
        uint rollNo;
        string name;
    }

    Student[] public students;

    function addStudent(uint _rollNo, string memory _name) public 
    {
        students.push(Student(_rollNo, _name));
    }

    function getStudent(uint index) public view returns (uint, string memory)
    {
        require(index < students.length, "invalid");
        Student memory s = students[index];

        return (s.rollNo, s.name);
    }

    function getStudentCount() public view returns (uint)
    {
        return students.length;
    }
}