//SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BankAccount
{
    mapping(address => uint) private balances;

    function deposit() public payable {
        balances[msg.sender] += msg.value;
    }

    function withdraw(uint amount) public {
        require(balances[msg.sender] >= amount, "Insufficient");
        balances[msg.sender] -= amount;
        (bool success, ) = payable(msg.sender).call{value : amount}("");
        require(success, "failed");
    }

    function getBalance() public view returns (uint) {
        return balances[msg.sender];
    }
}